package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class MdmcTaskItemLogDto implements Serializable {
    @ApiModelProperty("工单编号")
    private Long taskId;

    @ApiModelProperty("操作描述")
    private String description;
}
