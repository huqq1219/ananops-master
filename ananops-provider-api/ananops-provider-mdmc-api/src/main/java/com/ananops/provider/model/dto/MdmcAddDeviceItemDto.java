package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel
public class MdmcAddDeviceItemDto implements Serializable {

    @ApiModelProperty(value = "备品子任务ID")
    private Long id;

    @ApiModelProperty("从属的备件单编号")
    private Long deviceOrderId;

    @ApiModelProperty("从属的子任务id")
    private Long taskItemId;

    @ApiModelProperty("备件编号")
    private Long deviceId;

    @ApiModelProperty("备件类型")
    private String deviceType;

    @ApiModelProperty("备件数量")
    private Integer count;

    @ApiModelProperty("备件单价")
    private BigDecimal cost;
}
