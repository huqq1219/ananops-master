package com.ananops.provider.web.rpc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.BaseController;
import com.ananops.provider.model.domain.Device;
import com.ananops.provider.model.domain.DeviceOrder;
import com.ananops.provider.model.dto.CreateNewOrderDto;
import com.ananops.provider.model.dto.ProcessOrderDto;
import com.ananops.provider.service.DeviceOrderService;
import com.ananops.provider.service.DeviceService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.Api;
import model.dto.CreateNewOrderFeignDto;
import model.dto.ProcessOrderFeignDto;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import service.RdcDeviceOrderFeignApi;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhs
 */

@RefreshScope
@RestController
@Api(value = "API - RdcDeviceOrderFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RdcDeviceOrderFeignClient extends BaseController implements RdcDeviceOrderFeignApi {

    @Resource
    DeviceOrderService deviceOrderService;

    @Resource
    DeviceService deviceService;

    @Override
    public Wrapper<Object> createOrder(CreateNewOrderFeignDto createNewOrderFeignDto) {
        LoginAuthDto loginAuthDto = createNewOrderFeignDto.getLoginAuthDto();
        CreateNewOrderDto createNewOrderDto = new CreateNewOrderDto();
        BeanUtils.copyProperties(createNewOrderFeignDto, createNewOrderDto);
        return WrapMapper.ok(deviceOrderService.createNewOrder(loginAuthDto, createNewOrderDto));
    }

    @Override
    public Wrapper<Object> operation(ProcessOrderFeignDto processOrderFeignDto) {
        LoginAuthDto loginAuthDto = processOrderFeignDto.getLoginAuthDto();
        ProcessOrderDto processOrderDto = new ProcessOrderDto();
        BeanUtils.copyProperties(processOrderFeignDto, processOrderDto);
        return WrapMapper.ok(deviceOrderService.processOrder(loginAuthDto, processOrderDto));
    }

    @Override
    public Wrapper<Object> getTodoDeviceOrderByUserId(Long userId) {
        return WrapMapper.ok(deviceOrderService.getOrderByApproverIdAndVersion(userId,1));
    }

    @Override
    public Wrapper<Object> getDoneDeviceOrderByUserId(Long userId) {
        return WrapMapper.ok(deviceOrderService.getOrderByApproverIdAndVersion(userId,0));
    }

    @Override
    public Wrapper<Object> getDeviceOrderByUserId(Long userId) {
        return WrapMapper.ok(deviceOrderService.getOrderByApproverIdAndVersion(userId,null));
    }

    @Override
    public Wrapper<Object> getDeviceOrderByObject(Long objectId, Integer objectType) {
        return WrapMapper.ok(deviceOrderService.getDeviceOrderByObject(objectId,objectType));
    }

    @Override
    public Wrapper<Object> getAllDevice() {
        return WrapMapper.ok(deviceService.selectAll());
    }

    @Override
    public Wrapper<Object> getTotalCost(Long orderId) {
        JSONObject ret = new JSONObject();
        DeviceOrder order = deviceOrderService.selectByKey(orderId);
        BigDecimal totalCost = order != null ? order.getTotalPrice() : null;
        ret.put("totalCost", totalCost);
        return WrapMapper.ok(ret);
    }

    @Override
    public Wrapper<Object> getPriceOfDevices(List<Long> deviceIdArray) {
        List<JSONObject> devicePriceJson = new ArrayList<>();
        deviceIdArray.forEach(id->{
            JSONObject item = new JSONObject();
            Device d = deviceService.getDeviceById(id);
            if(d != null) {
                item.put("deviceId", d.getId());
                item.put("price", d.getPrice());
            }
            devicePriceJson.add(item);
        });
        return WrapMapper.ok(devicePriceJson);
    }
}
