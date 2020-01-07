package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by rongshuai on 2019/12/3 8:22
 */
@Data
@ApiModel
public class ImcAddDeviceOrderDto implements Serializable {
    private static final long serialVersionUID = -7255977012891559528L;
    /**
     * 对应的巡检任务ID
     */
    @ApiModelProperty(value = "被巡检设备对应的任务ID")
    private Long inspectionTaskId;

    /**
     * 对应的巡检任务子项ID
     */
    @ApiModelProperty(value = "被巡检设备对应的任务子项ID")
    private Long inspectionItemId;

    /**
     * 当前备品备件订单的处理状态
     */
    @ApiModelProperty(value = "当前备品备件订单的处理状态")
    private Integer status;

    /**
     * 对该订单的处理意见
     */
    @ApiModelProperty(value = "对该订单的处理意见")
    private String comment;

    /**
     * 备品备件订单对应对的设备列表
     */
    @ApiModelProperty(value = "备品备件订单对应对的设备列表")
    private List<DeviceDto> deviceDtoList;

}
