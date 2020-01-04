package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class ActiDeployDto implements Serializable {
    @ApiModelProperty(value = "流程名称")
    private String name;

    @ApiModelProperty(value = "bpmn路径")
    private String bpmnpath;

    @ApiModelProperty(value = "png路径")
    private String pngpath;
}
