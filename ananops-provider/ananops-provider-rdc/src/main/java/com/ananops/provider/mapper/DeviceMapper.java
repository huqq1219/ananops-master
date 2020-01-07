package com.ananops.provider.mapper;

import com.ananops.core.mybatis.MyMapper;
import com.ananops.provider.model.domain.Device;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeviceMapper extends MyMapper<Device> {
}