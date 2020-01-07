package com.ananops.provider.manager;

import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.base.exception.BusinessException;
import com.ananops.provider.annotation.MqProducerStore;
import com.ananops.provider.mapper.MdmcTaskMapper;
import com.ananops.provider.model.domain.MdmcTask;
import com.ananops.provider.model.domain.MqMessageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Component
@Transactional(rollbackFor = Exception.class)
public class MdmcTaskManager {
    @Resource
    MdmcTaskMapper taskMapper;

    @MqProducerStore
    public void saveTask(final MqMessageData mqMessageData, MdmcTask task, boolean addFlag){
        log.info("保存维修任务. mqMessageData={}, imcInspectionTask={}",mqMessageData,task);
        if(addFlag){
            taskMapper.insert(task);
        }else{
            int result =taskMapper.updateByPrimaryKeySelective(task);
            if(result < 1){
                throw new BusinessException(ErrorCodeEnum.GL9999093);
            }
        }
    }
    @MqProducerStore
    public void modifyTaskStatus(final MqMessageData mqMessageData,MdmcTask task){
        log.info("修改任务状态. mqMessageData={},imcTaskChangeStatusDto={}",mqMessageData,task);
        int result = taskMapper.updateByPrimaryKeySelective(task);
        if(result < 1){
            throw new BusinessException(ErrorCodeEnum.GL9999092);
        }
    }



}
