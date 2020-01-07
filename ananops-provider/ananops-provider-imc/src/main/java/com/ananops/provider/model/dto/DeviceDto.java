package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by rongshuai on 2020/1/3 22:08
 */
@Data
@ApiModel
public class DeviceDto implements Serializable {
    private static final long serialVersionUID = 2811662907912336606L;

    /**
     * 设备的ID
     */
    @ApiModelProperty(value = "设备的ID")
    private Long deviceId;

    /**
     * 当前订单花费
     */
    @ApiModelProperty(value = "当前订单花费")
    private BigDecimal cost;

    /**
     * 设备类型
     */
    @ApiModelProperty(value = "设备类型")
    private String deviceType;

    /**
     * 设别生产商
     */
    @ApiModelProperty(value = "设别生产商")
    private String manufacture;

    /**
     * 设备型号
     */
    @ApiModelProperty(value = "设备型号")
    private String deviceModel;

    /**
     * 设备对应的订单ID
     */
    @ApiModelProperty(value = "设备对应的订单ID")
    private Long orderId;
}
