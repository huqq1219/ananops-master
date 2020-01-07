package com.ananops.provider.service;

import com.alibaba.fastjson.JSONObject;
import com.ananops.core.support.IService;
import com.ananops.provider.model.domain.Device;

import java.util.List;

public interface DeviceService extends IService<Device> {
    
    List<Device> getAllDevicesSelective(JSONObject json);
    
    Device getDeviceById(Long id);
    
    int addDevice(JSONObject json);
    
    int updateDevice(JSONObject json);
}
