package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 设备信息展示页，一个task 内包含很多taskItem , 用于显示item列表
 */
@Data
@ApiModel
public class MdmcTaskItemDto implements Serializable {
    private static final long serialVersionUID = -3208778407302267489L;
    @ApiModelProperty("工单id")
    private Long taskId;

    @ApiModelProperty("设备编号")
    private Long deviceId;

    @ApiModelProperty("设备名称")
    private String deviceName;

    @ApiModelProperty("故障等级")
    private String level;

    @ApiModelProperty("故障描述")
    private String description;

    @ApiModelProperty("设备地址-经度")
    private BigDecimal deviceLatitude;

    @ApiModelProperty("设备地址-纬度")
    private BigDecimal deviceLongitude;

    @ApiModelProperty("图片地址")
    private String photo_url;

    @ApiModelProperty("故障类型")
    private Integer trouble_type;

//    @ApiModelProperty("图片上传链接")
//    private String imageUrl;
//
//    @ApiModelProperty("音频上传链接")
//    private String audioUrl;
//
//    @ApiModelProperty("视频上传链接")
//    private String videoUrl;
//
//    @ApiModelProperty("人工维修费用")
//    private BigDecimal laborCost;

}
