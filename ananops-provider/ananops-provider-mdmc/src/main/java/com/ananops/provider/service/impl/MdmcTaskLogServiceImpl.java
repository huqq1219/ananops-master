package com.ananops.provider.service.impl;

import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.base.exception.BusinessException;
import com.ananops.provider.mapper.MdmcTaskLogMapper;
import com.ananops.provider.model.domain.MdmcTaskLog;

import com.ananops.provider.service.MdmcTaskLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class MdmcTaskLogServiceImpl implements MdmcTaskLogService {

    @Resource
    MdmcTaskLogMapper taskLogMapper;


    @Override
    public List<MdmcTaskLog> getTaskLogsByTaskId(Long task_id) {
        Example example1 = new Example(MdmcTaskLog.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("taskId",task_id);
        if(taskLogMapper.selectCountByExample(example1)==0){
            //如果查询的任务不存在
            throw new BusinessException(ErrorCodeEnum.GL9999098,task_id);
        }

        return taskLogMapper.selectByExample(example1);
    }
}
