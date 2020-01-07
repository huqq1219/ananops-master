package com.ananops.provider.web.frontend;


import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.BaseController;
import com.ananops.provider.model.domain.MdmcDevice;
import com.ananops.provider.model.domain.MdmcDeviceOrder;
import com.ananops.provider.model.dto.MdmcAddDeviceDto;
import com.ananops.provider.model.dto.MdmcStatusDto;
import com.ananops.provider.service.MdmcDeviceService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/mdmcDevice",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - MdmcDevice",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MdmcDeviceController extends BaseController {

    @Resource
    MdmcDeviceService deviceService;

    @PostMapping(value = "/save")
    @ApiOperation(httpMethod = "POST",value = "编辑备品备件订单")
    public Wrapper<MdmcDevice> saveDeviceOrder(@ApiParam(name = "saveDeviceOrder",value = "编辑备品备件")@RequestBody MdmcAddDeviceDto addDeviceDto){
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        return WrapMapper.ok(deviceService.saveDevice(addDeviceDto,loginAuthDto));
    }
    @GetMapping(value = "/getDeviceByItemId/{itemId}")
    @ApiOperation(httpMethod = "GET",value = "获取任务子项对应的备品备件")
    public Wrapper<List<MdmcDevice>> getDeviceByItemId(@PathVariable("itemId") Long itemId){
        Example example = new Example(MdmcDevice.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("taskItemId",itemId);
        List<MdmcDevice> deviceList = deviceService.selectByExample(example);
        return WrapMapper.ok(deviceList);
    }

    @PostMapping(value = "/getDeviceByTaskId")
    @ApiOperation(httpMethod = "POST",value = "获取任务对应的备品备件列表")
    public Wrapper<List<MdmcDevice>> getDeviceOrderByTaskId(@RequestBody MdmcStatusDto statusDto){
        Example example = new Example(MdmcDevice.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("taskId",statusDto.getTaskId());
        PageHelper.startPage(statusDto.getPageNum(),statusDto.getPageSize());
        List<MdmcDevice> deviceList = deviceService.selectByExample(example);
        return WrapMapper.ok(deviceList);
    }

}
