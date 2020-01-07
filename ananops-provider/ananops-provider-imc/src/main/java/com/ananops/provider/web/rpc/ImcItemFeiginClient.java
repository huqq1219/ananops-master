package com.ananops.provider.web.rpc;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.base.exception.BusinessException;
import com.ananops.core.support.BaseController;
import com.ananops.provider.mapper.ImcInspectionItemMapper;
import com.ananops.provider.model.domain.ImcInspectionItem;
import com.ananops.provider.model.dto.*;
import com.ananops.provider.model.enums.ItemStatusEnum;
import com.ananops.provider.model.enums.TaskStatusEnum;
import com.ananops.provider.service.ImcInspectionItemService;
import com.ananops.provider.service.ImcInspectionTaskService;
import com.ananops.provider.service.ImcItemFeignApi;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;


/**
 * Created by rongshuai on 2019/12/18 10:20
 */
@RefreshScope
@RestController
@Api(value = "API - ImcProjectQueryFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ImcItemFeiginClient extends BaseController implements ImcItemFeignApi {
    @Resource
    ImcInspectionTaskService imcInspectionTaskService;
    @Resource
    ImcInspectionItemService imcInspectionItemService;
    @Resource
    ImcInspectionItemMapper imcInspectionItemMapper;

    @Override
    @ApiOperation(httpMethod = "POST", value = "修改巡检任务子项对应的工程师")
    public Wrapper<ItemChangeMaintainerDto> modifyMaintainerByItemId(@ApiParam(name = "modifyMaintainerByItemId",value = "修改巡检任务子项对应的工程师ID")@RequestBody ItemChangeMaintainerDto itemChangeMaintainerDto){
        Long itemId = itemChangeMaintainerDto.getItemId();
        Long maintainerId = itemChangeMaintainerDto.getMaintainerId();
        Example example = new Example(ImcInspectionItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",itemId);
        if(imcInspectionItemMapper.selectCountByExample(example)==0){
            throw new BusinessException(ErrorCodeEnum.GL9999097);
        }
        ImcInspectionItem imcInspectionItem = imcInspectionItemService.getItemByItemId(itemId);
        imcInspectionItem.setMaintainerId(maintainerId);
        int result = imcInspectionItemService.update(imcInspectionItem);
        if(result == 1){
            return WrapMapper.ok(itemChangeMaintainerDto);
        }
        throw new BusinessException(ErrorCodeEnum.GL9999093);
    }

    /**
     * 工程师拒单
     * @param refuseItemDto
     * @return
     */
    @Override
    @ApiOperation(httpMethod = "POST", value = "工程师拒单（任务子项）")
    public Wrapper<ImcItemChangeStatusDto> refuseImcItemByItemId(@ApiParam(name = "refuseImcItemByItemId",value = "维修工拒单（巡检任务子项）")@RequestBody RefuseItemDto refuseItemDto){
        LoginAuthDto loginAuthDto = refuseItemDto.getLoginAuthDto();
        Long itemId = refuseItemDto.getItemId();
        ImcItemChangeStatusDto imcItemChangeStatusDto = new ImcItemChangeStatusDto();
        //将任务子项状态重新修改为等待分配维修工
        imcItemChangeStatusDto.setStatus(ItemStatusEnum.WAITING_FOR_MAINTAINER.getStatusNum());
        imcItemChangeStatusDto.setItemId(itemId);
        imcItemChangeStatusDto.setStatusMsg(ItemStatusEnum.WAITING_FOR_MAINTAINER.getStatusMsg());
        Example example = new Example(ImcInspectionItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",itemId);
        if(imcInspectionItemService.selectCountByExample(example)==0){
            //如果当前巡检任务子项不存在
            throw new BusinessException(ErrorCodeEnum.GL9999097,itemId);
        }
        //如果当前巡检任务子项存在
        ImcInspectionItem imcInspectionItem = imcInspectionItemService.selectByExample(example).get(0);
        if(imcInspectionItem.getStatus().equals(ItemStatusEnum.WAITING_FOR_ACCEPT.getStatusNum())){
            //如果当前巡检任务子项的状态处于等待工程师接单，才可以进行拒单操作
            imcInspectionItem.setStatus(ItemStatusEnum.WAITING_FOR_MAINTAINER.getStatusNum());
            imcInspectionItem.setUpdateInfo(loginAuthDto);
            imcInspectionItemService.update(imcInspectionItem);//更新当前巡检任务子项的状态
        }else{
            //否则的话，不能进行拒单
            throw new BusinessException(ErrorCodeEnum.GL9999093);
        }
        return WrapMapper.ok(imcItemChangeStatusDto);
    }

    /**
     * 修改巡检任务子项的状态
     * @param imcItemChangeStatusDto
     * @return
     */
    @Override
    public Wrapper<ImcItemChangeStatusDto> modifyImcItemStatus(@ApiParam(name = "modifyImcItemStatus",value = "修改巡检任务子项的状态")@RequestBody ImcItemChangeStatusDto imcItemChangeStatusDto){
        imcItemChangeStatusDto.setStatusMsg(ItemStatusEnum.getStatusMsg(imcItemChangeStatusDto.getStatus()));
        Long itemId = imcItemChangeStatusDto.getItemId();
        int status = imcItemChangeStatusDto.getStatus();
        LoginAuthDto loginAuthDto = imcItemChangeStatusDto.getLoginAuthDto();
        Example example = new Example(ImcInspectionItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",itemId);
        if(imcInspectionItemService.selectCountByExample(example)==0){
            //如果当前巡检任务子项不存在
            throw new BusinessException(ErrorCodeEnum.GL9999097);
        }
        //如果当前巡检任务子项存在
        ImcInspectionItem imcInspectionItem = new ImcInspectionItem();
        imcInspectionItem.setId(itemId);
        imcInspectionItem.setStatus(ItemStatusEnum.WAITING_FOR_MAINTAINER.getStatusNum());
        imcInspectionItem.setUpdateInfo(loginAuthDto);
        imcInspectionItemService.update(imcInspectionItem);//更新当前巡检任务子项的状态
        Long taskId = imcInspectionItemService.getItemByItemId(itemId).getInspectionTaskId();
        if(status==ItemStatusEnum.INSPECTION_OVER.getStatusNum() && imcInspectionTaskService.isTaskFinish(taskId)){
            //如果该巡检子项对应的巡检任务中全部的任务子项均已完成
            //则修改对应的巡检任务状态为已完成
            ImcTaskChangeStatusDto imcTaskChangeStatusDto = new ImcTaskChangeStatusDto();
            imcTaskChangeStatusDto.setTaskId(taskId);
            imcTaskChangeStatusDto.setStatus(TaskStatusEnum.WAITING_FOR_CONFIRM.getStatusNum());//将巡检任务状态修改为“巡检结果待审核”
            imcInspectionTaskService.modifyTaskStatus(imcTaskChangeStatusDto,loginAuthDto);
        }
        return WrapMapper.ok(imcItemChangeStatusDto);
    }
}
