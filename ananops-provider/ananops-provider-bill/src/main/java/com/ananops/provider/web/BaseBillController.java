package com.ananops.provider.web;

import com.alibaba.fastjson.JSONObject;
import com.ananops.provider.model.domain.Basebill;
import com.ananops.provider.model.dto.*;
import com.ananops.provider.service.PmcContractFeignApi;
import com.ananops.provider.service.impl.BaseServiceImpl;
import com.ananops.provider.utils.WrapMapper;
import com.ananops.provider.utils.Wrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.*;
import service.RdcDeviceOrderFeignApi;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bill")
@Slf4j
public class BaseBillController {

//    @Resource
//    RdcDeviceOrderFeignApi rdcDeviceOrderFeignApi;
    @Resource
    PmcContractFeignApi pmcContractFeignApi;
    @Resource
    BaseServiceImpl baseServiceImpl;

    @PostMapping(value = "/create")
    @ApiOperation(httpMethod = "POST",value = "创建账单")
    public Wrapper<String> createNew(@ApiParam(name = "body",value="账单信息") @RequestBody BillCreateDto abc){
        PmcPayDto pmcPayDto = new PmcPayDto();
        com.ananops.wrapper.Wrapper<List<PmcPayDto>> list = pmcContractFeignApi.getContactByAB(Long.valueOf(abc.getUserid()),Long.valueOf(abc.getSupplier()));
        if (list.getResult() == null) {
            log.info("该用户与该服务商没有合同");
        }
        List<PmcPayDto> payDtoList = new ArrayList<>(list.getResult());
        for (PmcPayDto payDto : payDtoList) {
            try {
                BeanUtils.copyProperties(pmcPayDto, payDto);
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
        Float servicePrice = pmcPayDto.getPaymentMoney().floatValue();
        String transactionMethod = pmcPayDto.getPaymentType().toString();
        List<DeviceDto> deviceDtoList = abc.getDeviceDtos();
        List<Long> deviceIds = new ArrayList<>();
        for (DeviceDto deviceDto : deviceDtoList) {
            deviceIds.add(deviceDto.getDeviceId());
        }
//        com.ananops.wrapper.Wrapper<Object> objects = rdcDeviceOrderFeignApi.getPriceOfDevices(deviceIds);
//        List<JSONObject> jsonObjects = (List<JSONObject>)objects.getResult();
        Float devicePrice = 0.0F;
//        for (JSONObject object : jsonObjects) {
//            devicePrice += object.getBigDecimal("price").floatValue();
//        }

        baseServiceImpl.insert(abc, devicePrice, servicePrice, transactionMethod);
        return  WrapMapper.ok("success");
    }

    @PostMapping(value = "/createFakeOrder")
    @ApiOperation(httpMethod = "POST",value = "创建账单")
    public Wrapper<Float> createFakeNew(@ApiParam(name = "body",value="账单信息") @RequestBody BillCreateDto abc){
        PmcPayDto pmcPayDto = new PmcPayDto();
        com.ananops.wrapper.Wrapper<List<PmcPayDto>> list = pmcContractFeignApi.getContactByAB(Long.valueOf(abc.getUserid()),Long.valueOf(abc.getSupplier()));
        if (list.getResult() == null) {
            log.info("该用户与该服务商没有合同");
        }
        List<PmcPayDto> payDtoList = new ArrayList<>(list.getResult());
        for (PmcPayDto payDto : payDtoList) {
            try {
                BeanUtils.copyProperties(pmcPayDto, payDto);
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
        Float servicePrice = pmcPayDto.getPaymentMoney().floatValue();
//        List<DeviceDto> deviceDtoList = abc.getDeviceDtos();
//        List<Long> deviceIds = new ArrayList<>();
//        for (DeviceDto deviceDto : deviceDtoList) {
//            deviceIds.add(deviceDto.getDeviceId());
//        }
//        com.ananops.wrapper.Wrapper<Object> objects = rdcDeviceOrderFeignApi.getPriceOfDevices(deviceIds);
//        List<JSONObject> jsonObjects = (List<JSONObject>)objects.getResult();
        Float devicePrice = 0.0F;
//        for (JSONObject object : jsonObjects) {
//            devicePrice += object.getBigDecimal("price").floatValue();
//        }

        return  WrapMapper.ok(servicePrice + devicePrice);
    }

    @GetMapping(value = "/getallbyuser/{userid}")
    @ApiOperation(httpMethod = "GET",value = "根据用户id获取所有账单")
    public  Wrapper<List<Basebill>> getAllBillByUserId(@ApiParam(name = "userid",value = "用户id") @RequestParam Long userid){
        return WrapMapper.ok(baseServiceImpl.getAllBillByUserId(userid.toString()));
    }

    @GetMapping(value = "/getamountbyworkorder/{workorderid}")
    @ApiOperation(httpMethod = "GET",value = "根据工单id获取金额")
    public Wrapper<Float> getAmountByworkorderid(@ApiParam(name = "workorderid",value = "工单id") @RequestParam Long workorderid){
        return WrapMapper.ok(baseServiceImpl.getAmountByworkorderid(workorderid.toString()));
    }

    @PutMapping(value = "/modifyamount")
    @ApiOperation(httpMethod = "PUT",value = "修改金额")
    public void modifyAmount(@ApiParam(name = "modifyamount",value = "修改金额") @RequestBody BillModifyAmountDto billModifyAmountDto){
        Basebill basebill = baseServiceImpl.getBillById(billModifyAmountDto.getId());
        baseServiceImpl.modifyAmount(basebill, billModifyAmountDto.getModifyAmount());
    }

    @GetMapping(value = "/getubill/{userid}/{state}")
    @ApiOperation(httpMethod = "GET",value = "根据状态及用户id获取账单")
    public Wrapper<List<Basebill>> getAllUBillBystate(@ApiParam(name = "userid",value = "用户id") @RequestParam Long userid,
                                             @ApiParam(name = "state",value = "状态") @RequestParam String state){
        return WrapMapper.ok(baseServiceImpl.getAllUBillBystate(userid.toString(), state));
    }

    @GetMapping(value = "/getBillByWorkOrderId/{workorderid}")
    @ApiOperation(httpMethod = "GET",value = "根据状态及用户id获取账单")
    public Wrapper<List<Basebill>> getBillByWorkOrderId(@ApiParam(name = "workorderid",value = "工单id") @RequestParam Long workorderid){
        return WrapMapper.ok(baseServiceImpl.getBillByWorkOrderId(workorderid.toString()));
    }
}
