package com.ananops.provider.service.impl;

import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.base.exception.BusinessException;
import com.ananops.core.support.BaseService;
import com.ananops.provider.mapper.MdmcTaskItemLogMapper;
import com.ananops.provider.model.domain.MdmcTaskItem;
import com.ananops.provider.model.domain.MdmcTaskItemLog;
import com.ananops.provider.service.MdmcTaskItemLogService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MdmcTaskItemLogServiceImpl extends BaseService<MdmcTaskItemLog> implements MdmcTaskItemLogService {
    @Resource
    MdmcTaskItemLogMapper taskItemLogMapper;
    @Override
    public List<MdmcTaskItemLog> getTaskItemLogsByTaskItemId(Long taskItemId) {
        Example example1 = new Example(MdmcTaskItemLog.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("taskItemId",taskItemId);
        if(taskItemLogMapper.selectCountByExample(example1)==0){
            //如果查询的任务不存在
            throw new BusinessException(ErrorCodeEnum.GL9999098,taskItemId);
        }

        return taskItemLogMapper.selectByExample(example1);
    }
}
