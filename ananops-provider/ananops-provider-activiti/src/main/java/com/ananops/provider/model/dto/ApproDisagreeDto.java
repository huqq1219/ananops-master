package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class ApproDisagreeDto implements Serializable {
    @ApiModelProperty(value = "任务id")
    private String taskId;

    @ApiModelProperty(value = "批注")
    private String comment;
}
