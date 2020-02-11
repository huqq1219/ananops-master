package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel
public class MdmcAddDeviceDto implements Serializable {
    @ApiModelProperty(value = "备品备件ID")
    private Long id;

    @ApiModelProperty("从属的任务id")
    private Long taskId;

    @ApiModelProperty("维修工id")
    private Long maintainerId;

    @ApiModelProperty("甲方id")
    private Long principalId;

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
