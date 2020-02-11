package com.ananops.provider.service.impl;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.base.exception.BusinessException;
import com.ananops.core.support.BaseService;
import com.ananops.provider.mapper.MdmcDeviceMapper;
import com.ananops.provider.mapper.MdmcTaskItemMapper;
import com.ananops.provider.mapper.MdmcTaskMapper;
import com.ananops.provider.model.domain.MdmcDevice;
import com.ananops.provider.model.domain.MdmcTask;
import com.ananops.provider.model.domain.MdmcTaskItem;
import com.ananops.provider.model.dto.MdmcAddDeviceDto;
import com.ananops.provider.service.MdmcDeviceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;


@Service
public class MdmcDeviceServiceImpl extends BaseService<MdmcDevice> implements MdmcDeviceService {

    @Resource
    MdmcTaskMapper taskMapper;

    @Resource
    MdmcTaskItemMapper taskItemMapper;

    @Resource
    MdmcDeviceMapper deviceMapper;
    @Override
    public MdmcDevice saveDevice(MdmcAddDeviceDto addDeviceDto,LoginAuthDto loginAuthDto) {
        MdmcDevice device=new MdmcDevice();
        BeanUtils.copyProperties(addDeviceDto,device);
        device.setUpdateInfo(loginAuthDto);
        Long taskId =device.getTaskId();
        MdmcTask task=taskMapper.selectByPrimaryKey(taskId);
        task.setStatus(6);
        taskMapper.updateByPrimaryKey(task);
        Example example1 = new Example(MdmcTask.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("id",taskId);
        if(taskMapper.selectCountByExample(example1)==0){//如果备品备件订单对应的巡检任务不存在
            throw new BusinessException(ErrorCodeEnum.GL9999098,taskId);
        }
        Long taskItemId=device.getTaskItemId();
        MdmcTaskItem taskItem=taskItemMapper.selectByPrimaryKey(taskItemId);
        taskItem.setStatus(2);
        taskItemMapper.updateByPrimaryKey(taskItem);
        Example example2 = new Example(MdmcTaskItem.class);
        Example.Criteria criteria2 = example2.createCriteria();
        criteria1.andEqualTo("id",taskItemId);
        if(taskMapper.selectCountByExample(example2)==0){//如果备品备件订单对应的巡检任务不存在
            throw new BusinessException(ErrorCodeEnum.GL9999097,taskItemId);
        }

        if(device.isNew()){//如果需要新增一条备品备件订单
            Long deviceId = super.generateId();
            device.setId(deviceId);
            deviceMapper.insert(device);
        }else{//如果需要修改已经存在的备品备件订单
            deviceMapper.updateByPrimaryKeySelective(device);//只更新变化的字段
        }

        return device;
    }
}
