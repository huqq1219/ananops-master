package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel
public class DeployListDto implements Serializable {
    @ApiModelProperty(value = "流程定义id")
    private String processDefinitionId;

    @ApiModelProperty(value = "流程定义名称")
    private String processDefinitionKey;

    @ApiModelProperty(value = "版本")
    private int version;
}
