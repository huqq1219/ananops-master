package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class ActiStartDto implements Serializable {
    @ApiModelProperty(value = "当前用户id")
    private Long userid;

    @ApiModelProperty(value = "流程定义id")
    private String processDefinitionId;

}
