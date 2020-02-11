package com.ananops.provider.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel
public class DeviceVo implements Serializable {
    private static final long serialVersionUID = -5711468008814085690L;

    @ApiModelProperty("设备编号")
    @JsonProperty("deviceId")
    private Long id;

    @ApiModelProperty("设备名称")
    private String name;

    @ApiModelProperty("设备类型")
    private String type;

    @ApiModelProperty("设备厂商")
    private String manufacture;

    @ApiModelProperty("设备型号")
    private String model;

    @ApiModelProperty("申请数量")
    private Integer count;

    @ApiModelProperty("设备单价")
    private BigDecimal price;

}
