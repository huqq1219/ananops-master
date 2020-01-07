package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class MdmcItemChangeStatusDto implements Serializable {
    /**
     * 任务子项的ID
     */
    @ApiModelProperty(value = "任务子项ID")
    private Long itemId;

    /**
     * 任务子项修改后的状态
     */
    @ApiModelProperty(value = "任务子项修改后的状态")
    private Integer status;

    /**
     * 任务子项修改后的状态描述
     */
    @ApiModelProperty(value = "任务子项修改后的状态描述")
    private String statusMsg;
}
