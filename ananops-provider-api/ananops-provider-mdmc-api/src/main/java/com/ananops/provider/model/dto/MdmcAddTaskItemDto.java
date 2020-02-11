package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
@ApiModel
public class MdmcAddTaskItemDto implements Serializable {

    private static final long serialVersionUID = 8604444543573834036L;

    @ApiModelProperty(value = "任务子项ID")
    private Long id;

    @ApiModelProperty("设备编号")
    private Long deviceId;

    @ApiModelProperty("设备编号")
    private String deviceName;

    @ApiModelProperty("设备类型")
    private String deviceType;

    @ApiModelProperty("工单id")
    private Long taskId;

    @ApiModelProperty("故障等级")
    private String level;

    @ApiModelProperty("故障描述")
    private String description;

    @ApiModelProperty("设备地址-经度")
    private BigDecimal deviceLatitude;

    @ApiModelProperty("设备地址-纬度")
    private BigDecimal deviceLongitude;

    @ApiModelProperty("故障类型")
    private Integer troubleType;
}
