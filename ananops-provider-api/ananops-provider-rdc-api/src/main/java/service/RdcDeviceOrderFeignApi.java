package service;

import com.ananops.security.feign.OAuth2FeignAutoConfiguration;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import model.dto.CreateNewOrderFeignDto;
import model.dto.ProcessOrderFeignDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import service.hystrix.RdcDeviceOrderFeignApiFeignHystrix;

import java.util.List;


/**
 * @author zhs
 */

@FeignClient(value = "ananops-provider-rdc", configuration = OAuth2FeignAutoConfiguration.class, fallback = RdcDeviceOrderFeignApiFeignHystrix.class)
public interface RdcDeviceOrderFeignApi {

    @PostMapping(value = "api/deviceOrder/create", produces = "application/json")
    Wrapper<Object> createOrder(@ApiParam("订单详情")@RequestBody CreateNewOrderFeignDto createNewOrderFeignDto);

    @PostMapping(value = "api/deviceOrder/operation", produces = "application/json")
    Wrapper<Object> operation(@ApiParam("处理详情")@RequestBody ProcessOrderFeignDto processOrderDto);

    @PostMapping(value = "/todo", produces = "application/json")
    Wrapper<Object> getTodoDeviceOrderByUserId(@ApiParam("当前用户编号")@RequestParam("userId")Long userId);

    @PostMapping(value = "/done", produces = "application/json")
    Wrapper<Object> getDoneDeviceOrderByUserId(@ApiParam("当前用户编号")@RequestParam("userId")Long userId);

    @PostMapping(value = "/all", produces = "application/json")
    Wrapper<Object> getDeviceOrderByUserId(@ApiParam("当前用户编号")@RequestParam("userId")Long userId);

    @PostMapping(value = "/all/object", produces = "application/json")
    Wrapper<Object> getDeviceOrderByObject(@ApiParam("备品备件订单来源对象的编号")@RequestParam("objectId")Long objectId,
                                           @ApiParam("备品备件订单来源对象的类型（维修维护填1）")@RequestParam("objectType")Integer objectType);

    @PostMapping(value = "/devices")
    Wrapper<Object> getAllDevice();

    @ApiOperation(value="获取备件订单总价格")
    @PostMapping(value = "/totalCost/{orderId}")
    Wrapper<Object> getTotalCost(@ApiParam(name="orderId", value = "订单编号")@PathVariable("orderId")Long orderId);

    @ApiOperation("获取备件（列表）对应的单价")
    @PostMapping(value = "/price")
    Wrapper<Object> getPriceOfDevices(@ApiParam(name = "device ids", value = "备件编号列表")@RequestBody List<Long> deviceIdArray);
}
