package com.ananops.provider.service.impl;


import com.ananops.base.dto.LoginAuthDto;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.base.exception.BusinessException;
import com.ananops.core.support.BaseService;
import com.ananops.provider.mapper.ImcInspectionItemLogMapper;
import com.ananops.provider.model.domain.ImcInspectionItemLog;
import com.ananops.provider.model.dto.ItemLogQueryDto;
import com.ananops.provider.model.enums.ItemStatusEnum;
import com.ananops.provider.model.vo.ItemLogVo;
import com.ananops.provider.service.ImcInspectionItemLogService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rongshuai on 2019/11/28 15:31
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ImcInspectionItemLogServiceImpl extends BaseService<ImcInspectionItemLog> implements ImcInspectionItemLogService {
    @Resource
    ImcInspectionItemLogMapper imcInspectionItemLogMapper;

    public Integer createInspectionItemLog(ImcInspectionItemLog imcInspectionItemLog, LoginAuthDto loginAuthDto){
        Long itemLogId = super.generateId();
        imcInspectionItemLog.setUpdateInfo(loginAuthDto);
        imcInspectionItemLog.setId(itemLogId);
        return imcInspectionItemLogMapper.insert(imcInspectionItemLog);
    }

    public List<ItemLogVo> getItemLogs(ItemLogQueryDto itemLogQueryDto){
        ImcInspectionItemLog imcInspectionItemLog = new ImcInspectionItemLog();
        Long itemId = itemLogQueryDto.getItemId();
        Example example = new Example(ImcInspectionItemLog.class);
        Example.Criteria criteria1 = example.createCriteria();
        criteria1.andEqualTo("itemId",itemId);
        if(imcInspectionItemLogMapper.selectCountByExample(example)==0){//如果被查询的任务子项不存在
            throw new BusinessException(ErrorCodeEnum.GL9999097);
        }
        Long taskId = imcInspectionItemLogMapper.selectByExample(example).get(0).getTaskId();
        imcInspectionItemLog.setTaskId(taskId);
        imcInspectionItemLog.setItemId(itemId);
        String orderBy = "status_timestamp asc";
        imcInspectionItemLog.setOrderBy(orderBy);
        PageHelper.startPage(itemLogQueryDto.getPageNum(),itemLogQueryDto.getPageSize());
        List<ItemLogVo> itemLogVoList = imcInspectionItemLogMapper.queryItemLogListWithPage(imcInspectionItemLog);
        for(int i=0;i<itemLogVoList.size();i++){
            int status = itemLogVoList.get(i).getStatus();
            itemLogVoList.get(i).setStatusMsg(ItemStatusEnum.getStatusMsg(status));
        }
        return itemLogVoList;
    }
}
