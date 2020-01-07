package com.ananops.provider.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class ApproSubmitDto implements Serializable {

    @ApiModelProperty(value = "当前用户id")
    private Long userid;

    @ApiModelProperty(value = "审批用户id")
    private Long nUserid;

    @ApiModelProperty(value = "流程定义id")
    private String processDefinitionId;

    @ApiModelProperty(value = "工单id")
    private Long orderId;

    @ApiModelProperty(value = "批注")
    private String comment;
}
