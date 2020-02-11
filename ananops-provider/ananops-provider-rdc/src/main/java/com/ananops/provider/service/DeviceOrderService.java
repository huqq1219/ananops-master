package com.ananops.provider.service;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.IService;
import com.ananops.provider.mapper.DeviceOrderMapper;
import com.ananops.provider.model.domain.DeviceOrder;
import com.ananops.provider.model.dto.CreateNewOrderDto;
import com.ananops.provider.model.dto.ProcessOrderDto;
import com.ananops.provider.model.vo.DeviceOrderDetailVo;
import com.ananops.provider.model.vo.DeviceOrderListVo;
import com.ananops.provider.model.vo.ProcessOrderResultVo;

import java.util.List;

public interface DeviceOrderService extends IService<DeviceOrder> {
    
    /**
     * 创建备品备件订单
     * @param loginAuthDto
     * @param createNewOrderDto
     * @return
     */
    ProcessOrderResultVo createNewOrder(LoginAuthDto loginAuthDto, CreateNewOrderDto createNewOrderDto);
    
    /**
     * 处理订单，包括处理意见， 报价
     * @param loginAuthDto
     * @param processOrderDto
     * @return
     */
    ProcessOrderResultVo processOrder(LoginAuthDto loginAuthDto, ProcessOrderDto processOrderDto);
    
    /**
     * 根据审批人获取所有待处理订单(version=0)
     * @param approverId
     * @return
     */
    List<DeviceOrder> getOrderByApproverIdAndVersion(Long approverId, Integer version);
    
    int getOrderCountByApproverIdAndVersion(Long approverId, Integer version);
    
    DeviceOrder getOrderByObjectIdAndObjectType(Long objectId, Integer objectType);

    DeviceOrderListVo getDeviceOrderByObject(Long objectId, Integer objectType);
    
}
