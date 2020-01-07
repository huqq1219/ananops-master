package com.ananops.provider.service.impl;


import com.ananops.base.dto.LoginAuthDto;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.base.exception.BusinessException;
import com.ananops.core.support.BaseService;
import com.ananops.provider.mapper.ImcInspectionItemMapper;
import com.ananops.provider.mapper.ImcInspectionTaskMapper;
import com.ananops.provider.mapper.ImcUserItemMapper;
import com.ananops.provider.model.domain.ImcInspectionItem;
import com.ananops.provider.model.domain.ImcInspectionTask;
import com.ananops.provider.model.domain.ImcUserItem;
import com.ananops.provider.model.dto.*;
import com.ananops.provider.model.enums.ItemStatusEnum;

import com.ananops.provider.model.enums.TaskStatusEnum;
import com.ananops.provider.service.ImcInspectionItemService;
import com.ananops.wrapper.WrapMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by rongshuai on 2019/11/28 10:14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ImcInspectionItemServiceImpl extends BaseService<ImcInspectionItem> implements ImcInspectionItemService {
    @Resource
    private ImcInspectionItemMapper imcInspectionItemMapper;

    @Resource
    private ImcInspectionTaskMapper imcInspectionTaskMapper;

    @Resource
    private ImcUserItemMapper imcUserItemMapper;

    /**
     *
     * @param imcAddInspectionItemDto
     * @return
     */
    public ImcAddInspectionItemDto saveInspectionItem(ImcAddInspectionItemDto imcAddInspectionItemDto, LoginAuthDto loginAuthDto){//编辑巡检任务子项记录
        ImcInspectionItem imcInspectionItem = new ImcInspectionItem();
        BeanUtils.copyProperties(imcAddInspectionItemDto,imcInspectionItem);
        imcInspectionItem.setUpdateInfo(loginAuthDto);
        Long taskId = imcInspectionItem.getInspectionTaskId();
        Long userId = imcAddInspectionItemDto.getUserId();
        Example example = new Example(ImcInspectionTask.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",taskId);
        List<ImcInspectionTask> imcInspectionTasks = imcInspectionTaskMapper.selectByExample(example);
        if(imcInspectionTasks.size()==0){//如果没有此巡检任务
            throw new BusinessException(ErrorCodeEnum.GL9999098,taskId);
        }
        if(imcInspectionItem.isNew()){//如果是新增一条巡检任务子项记录
            Long itemId = super.generateId();
            imcInspectionItem.setId(itemId);
            Long scheduledStartTime = imcInspectionItem.getScheduledStartTime().getTime();
            Long currentTime = System.currentTimeMillis();
            if(scheduledStartTime<=currentTime){
                //如果计划执行时间<=当前时间，说明，巡检任务需要立即执行
                //将巡检任务子项的状态设置为等待分配工程师
                imcInspectionItem.setStatus(ItemStatusEnum.WAITING_FOR_MAINTAINER.getStatusNum());
            }
            imcInspectionItemMapper.insert(imcInspectionItem);
            //新增一条巡检任务子项和甲方用户的关系记录
            ImcUserItem imcUserItem = new ImcUserItem();
            imcUserItem.setItemId(itemId);
            imcUserItem.setUserId(userId);
            imcUserItemMapper.insert(imcUserItem);

        }else{//如果是更新已经存在的巡检任务子项
            imcInspectionItemMapper.updateByPrimaryKeySelective(imcInspectionItem);
        }
        BeanUtils.copyProperties(imcInspectionItem,imcAddInspectionItemDto);
        return imcAddInspectionItemDto;
    }

    /**
     *
     * @param itemQueryDto
     * @return
     */
    public List<ImcInspectionItem> getAllItemByTaskId(ItemQueryDto itemQueryDto){
        Long taskId = itemQueryDto.getTaskId();
        Example example1 = new Example(ImcInspectionTask.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("id",taskId);
        if(imcInspectionTaskMapper.selectCountByExample(example1)==0){
            //如果查询的任务不存在
            throw new BusinessException(ErrorCodeEnum.GL9999098,taskId);
        }
        Example example2 = new Example(ImcInspectionItem.class);
        Example.Criteria criteria2 = example2.createCriteria();
        criteria2.andEqualTo("inspectionTaskId",taskId);
        PageHelper.startPage(itemQueryDto.getPageNum(),itemQueryDto.getPageSize());
        List<ImcInspectionItem> imcInspectionItems = imcInspectionItemMapper.selectByExample(example2);
        return imcInspectionItems;
    }

    /**
     *
     * @param itemId
     * @return
     */
    public ImcInspectionItem getItemByItemId(Long itemId){
        Example example = new Example(ImcInspectionItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",itemId);
        if(imcInspectionItemMapper.selectCountByExample(example)==0){
            throw new BusinessException(ErrorCodeEnum.GL9999097,itemId);
        }
        return imcInspectionItemMapper.selectByExample(example).get(0);
    }

    public List<ImcInspectionItem> getItemByItemStatusAndTaskId(ItemQueryDto itemQueryDto){
        Long taskId = itemQueryDto.getTaskId();
        Integer status = itemQueryDto.getStatus();
        Example example = new Example(ImcInspectionItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("inspectionTaskId",taskId);
        if(imcInspectionItemMapper.selectCountByExample(example)==0){
            throw new BusinessException(ErrorCodeEnum.GL9999098);
        }
        criteria.andEqualTo("status",status);
        if(imcInspectionItemMapper.selectCountByExample(example)==0){
            throw new BusinessException(ErrorCodeEnum.GL9999090);
        }
        PageHelper.startPage(itemQueryDto.getPageNum(),itemQueryDto.getPageSize());
        return imcInspectionItemMapper.selectByExample(example);
    }

    public List<ImcInspectionItem> getItemByUserId(ItemQueryDto itemQueryDto){
        PageHelper.startPage(itemQueryDto.getPageNum(),itemQueryDto.getPageSize());
        return imcInspectionItemMapper.queryItemByUserId(itemQueryDto.getUserId());
    }

    public List<ImcInspectionItem> getItemByUserIdAndStatus(ItemQueryDto itemQueryDto){
        PageHelper.startPage(itemQueryDto.getPageNum(),itemQueryDto.getPageSize());
        return imcInspectionItemMapper.queryItemByUserIdAndStatus(itemQueryDto.getUserId(),itemQueryDto.getStatus());
    }

    public List<ImcInspectionItem> getItemByMaintainerId(ItemQueryDto itemQueryDto){
        Long maintainerId = itemQueryDto.getMaintainerId();
        Example example = new Example(ImcInspectionItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("maintainerId",maintainerId);
        PageHelper.startPage(itemQueryDto.getPageNum(),itemQueryDto.getPageSize());
        return imcInspectionItemMapper.selectByExample(example);
    }

    public List<ImcInspectionItem> getItemByMaintainerIdAndStatus(ItemQueryDto itemQueryDto){
        Long maintainerId = itemQueryDto.getMaintainerId();
        Integer status = itemQueryDto.getStatus();
        Example example = new Example(ImcInspectionItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("maintainerId",maintainerId);
        criteria.andEqualTo("status",status);
        PageHelper.startPage(itemQueryDto.getPageNum(),itemQueryDto.getPageSize());
        return imcInspectionItemMapper.selectByExample(example);
    }

    /**
     * 修改巡检任务子项对应的维修工ID
     * @param itemChangeMaintainerDto
     * @return
     */
    public ItemChangeMaintainerDto modifyMaintainerIdByItemId(ItemChangeMaintainerDto itemChangeMaintainerDto){
        Long itemId = itemChangeMaintainerDto.getItemId();
        Long maintainerId = itemChangeMaintainerDto.getMaintainerId();
        Example example = new Example(ImcInspectionItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",itemId);
        if(imcInspectionItemMapper.selectCountByExample(example)==0){
            throw new BusinessException(ErrorCodeEnum.GL9999097);
        }
        ImcInspectionItem imcInspectionItem = this.getItemByItemId(itemId);
        imcInspectionItem.setMaintainerId(maintainerId);
        int result = this.update(imcInspectionItem);
        if(result == 1){
            return itemChangeMaintainerDto;
        }
        throw new BusinessException(ErrorCodeEnum.GL9999093);
    }
    public Integer setBasicInfoFromContract(){//将从合同中获取到的基本信息填写到巡检任务中
        return 1;
    }
}
