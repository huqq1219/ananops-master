package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


/*工单的设备故障信息*/
@Data
@ApiModel
public class MdmcDeviceTroubleDto implements Serializable {


    @ApiModelProperty("工单编号")
    private Long orderId;

    @ApiModelProperty("设备名称")
    private String deviceName;

    @ApiModelProperty("设备编号")
    private Long deviceNo;

//    @ApiModelProperty("设备型号")
//    private String deviceModel;
//
//    @ApiModelProperty("设备类型")
//    private String deviceType;
//
    @ApiModelProperty("设备纬度")
    private BigDecimal deviceLatitude;

    @ApiModelProperty("设备经度")
    private BigDecimal deviceLongtitude;

    @ApiModelProperty("报修人")
    private String creator;

//    @ApiModelProperty("创建时间")
//    private Date created_time;

    @ApiModelProperty("故障等级")
    private Integer level;

    @ApiModelProperty("故障描述")
    private String description;
}
