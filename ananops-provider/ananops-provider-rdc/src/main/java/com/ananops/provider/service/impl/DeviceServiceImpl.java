package com.ananops.provider.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ananops.core.support.BaseService;
import com.ananops.provider.model.domain.Device;
import com.ananops.provider.service.DeviceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl extends BaseService<Device> implements DeviceService {
    
    public List<Device> getAllDevicesSelective(JSONObject json) {
        Device device = new Device();
        BeanUtils.copyProperties(json, device);
        return select(device);
    }
    
    public Device getDeviceById(Long id) {
        return selectByKey(id);
    }
    
    public int addDevice(JSONObject json) {
        Device device = new Device();
        BeanUtils.copyProperties(json, device);
        return save(device);
    }
    
    public int updateDevice(JSONObject json){
        Device device = new Device();
        BeanUtils.copyProperties(json, device);
        return update(device);
    }
}
