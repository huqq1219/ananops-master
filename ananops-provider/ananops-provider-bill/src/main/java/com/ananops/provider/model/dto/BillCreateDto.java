package com.ananops.provider.model.dto;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BillCreateDto implements Serializable {
    @ApiModelProperty(value = "工单ID")
    private String workorderid;

    @ApiModelProperty(value = "用户ID")
    private String userid;

    @ApiModelProperty(value = "服务商ID")
    private String supplier;

    @ApiModelProperty(value = "支付方式") //填固定字段"未确定"
    private String paymentMethod;

    @ApiModelProperty(value = "状态") //填固定字段未支付
    private String state;

    @ApiModelProperty(value = "备品备件id和数量的列表")
    List<DeviceDto> deviceDtos;

}
