package com.ananops.provider.web.frontend;


import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.BaseController;
import com.ananops.provider.core.annotation.AnanLogAnnotation;
import com.ananops.provider.model.domain.ImcDevice;
import com.ananops.provider.model.domain.ImcDeviceOrder;
import com.ananops.provider.model.dto.DeviceOrderQueryDto;
import com.ananops.provider.model.dto.ImcAddDeviceOrderDto;
import com.ananops.provider.model.vo.DeviceOrderVo;
import com.ananops.provider.service.ImcDeviceOrderService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rongshuai on 2019/12/3 8:41
 */
@RestController
@RequestMapping(value = "/inspectionDeviceOrder",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - ImcInspectionDeviceOrderService",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ImcDeviceOrderController extends BaseController {
    @Resource
    ImcDeviceOrderService imcDeviceOrderService;

    @PostMapping(value = "/save")
    @ApiOperation(httpMethod = "POST",value = "编辑备品备件订单")
    @AnanLogAnnotation
    public Wrapper<ImcDeviceOrder> saveDeviceOrder(@ApiParam(name = "saveDeviceOrder",value = "编辑备品备件订单")@RequestBody ImcAddDeviceOrderDto imcAddDeviceOrderDto){
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        return WrapMapper.ok(imcDeviceOrderService.saveDeviceOrder(imcAddDeviceOrderDto,loginAuthDto));
    }

    @PostMapping(value = "/getDeviceOrderByItemId")
    @ApiOperation(httpMethod = "POST",value = "获取巡检任务子项对应的备品备件订单")
    public Wrapper<List<DeviceOrderVo>> getDeviceOrderByItemId(@ApiParam(name = "getDeviceOrderByItemId",value = "获取巡检任务子项对应的备品备件订单")@RequestBody DeviceOrderQueryDto deviceOrderQueryDto){
        return WrapMapper.ok(imcDeviceOrderService.getDeviceOrderByItemId(deviceOrderQueryDto));
    }

    @PostMapping(value = "/getDeviceOrderByTaskId")
    @ApiOperation(httpMethod = "POST",value = "获取巡检任务对应的备品备件订单")
    public Wrapper<List<DeviceOrderVo>> getDeviceOrderByTaskId(@ApiParam(name = "getDeviceOrderByTaskId",value = "获取巡检任务对应的备品备件订单")@RequestBody DeviceOrderQueryDto deviceOrderQueryDto){
        return WrapMapper.ok(imcDeviceOrderService.getDeviceOrderByTaskId(deviceOrderQueryDto));
    }

}
