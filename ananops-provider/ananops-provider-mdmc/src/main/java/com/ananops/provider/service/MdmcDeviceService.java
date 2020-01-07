package com.ananops.provider.service;


import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.IService;
import com.ananops.provider.model.domain.MdmcDevice;
import com.ananops.provider.model.domain.MdmcDeviceOrder;
import com.ananops.provider.model.dto.MdmcAddDeviceDto;

public interface MdmcDeviceService extends IService<MdmcDevice> {

    MdmcDevice saveDevice(MdmcAddDeviceDto addDeviceDto, LoginAuthDto loginAuthDto);


}
