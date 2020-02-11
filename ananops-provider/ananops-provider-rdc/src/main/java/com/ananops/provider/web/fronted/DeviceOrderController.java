package com.ananops.provider.web.fronted;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.BaseController;
import com.ananops.provider.model.dto.CreateNewOrderDto;
import com.ananops.provider.model.dto.ProcessOrderDto;
import com.ananops.provider.model.enums.DeviceOrderStatusEnum;
import com.ananops.provider.service.DeviceOrderService;
import com.ananops.provider.service.DeviceService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.zookeeper.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 备品备件订单
 * author: zhs
 */

@RestController
@RequestMapping("/deviceOrder")
public class DeviceOrderController extends BaseController {
    
    @Autowired
    DeviceOrderService orderService;

    @Autowired
    DeviceService deviceService;
    
    /**
     * 备品备件需求申请处理
     * 1.
     */
    @ApiOperation(httpMethod = "POST", value = "处理备品备件订单")
    @PostMapping(value = "/operation", produces = "application/json")
    public Wrapper<Object> operation(@ApiParam("处理详情")@RequestBody ProcessOrderDto processOrderDto) {
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        return WrapMapper.ok(orderService.processOrder(loginAuthDto, processOrderDto));
    }
    
    @ApiOperation(httpMethod = "POST", value = "创建备品备件订单")
    @PostMapping(value = "/create", produces = "application/json")
    public Wrapper<Object> createOrder(@ApiParam("订单详情")@RequestBody CreateNewOrderDto createNewOrderDto){
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        return WrapMapper.ok(orderService.createNewOrder(loginAuthDto, createNewOrderDto));
    }
    
    @ApiOperation(httpMethod = "GET", value = "获取当前用户待处理订单")
    @GetMapping(value = "/todo/{userId}", produces = "application/json")
    public Wrapper<Object> getTodoDeviceOrderByUserId(@ApiParam("当前用户编号")@PathVariable("userId")Long userId){
        return WrapMapper.ok(orderService.getOrderByApproverIdAndVersion(userId, 1));
    }
    
    @ApiOperation(httpMethod = "GET", value = "获取当前用户已处理订单")
    @GetMapping(value = "/done/{userId}", produces = "application/json")
    public Wrapper<Object> getDoneDeviceOrderByUserId(@ApiParam("当前用户编号")@PathVariable("userId")Long userId) {
        return WrapMapper.ok(orderService.getOrderByApproverIdAndVersion(userId, 0));
    }
    
    @ApiOperation(httpMethod = "GET", value = "获取用户所有相关备品备件订单")
    @GetMapping(value = "/all/{userId}", produces = "application/json")
    public Wrapper<Object> getDeviceOrderByUserId(@ApiParam("当前用户编号")@PathVariable("userId")Long userId){
        return WrapMapper.ok(orderService.getOrderByApproverIdAndVersion(userId, null));
    }
    
    @ApiOperation(httpMethod = "GET", value = "根据来源对象获取备品备件订单")
    @GetMapping(value = "/object/{objectId}/{objectType}", produces = "application/json")
    public Wrapper<Object> getDeviceOrderByObject(@ApiParam("备品备件订单来源对象的编号")@PathVariable("objectId")Long objectId,
                                                  @ApiParam("备品备件订单来源对象的类型（维修维护填1）")@PathVariable("objectType")Integer objectType){
        return WrapMapper.ok(orderService.getOrderByObjectIdAndObjectType(objectId, objectType));
    }

    @ApiOperation(httpMethod = "GET",value = "获取备件库所有设备")
    @GetMapping(value = "/devices")
    public Wrapper<Object> getAllDevice(){
        return WrapMapper.ok(deviceService.selectAll());
    }


    @ApiOperation(httpMethod = "GET", value = "根据绑定对象获取全部备品备件订单详情")
    @GetMapping(value = "/all/object/{objectId}/{objectType}")
    public Wrapper<Object> getAllDeviceOrderByObject(@ApiParam("备品备件订单来源对象的编号")@PathVariable("objectId")Long objectId,
                                                     @ApiParam("备品备件订单来源对象的类型（维修维护填1）")@PathVariable("objectType")Integer objectType){
        return WrapMapper.ok(orderService.getDeviceOrderByObject(objectId, objectType));
    }
}
