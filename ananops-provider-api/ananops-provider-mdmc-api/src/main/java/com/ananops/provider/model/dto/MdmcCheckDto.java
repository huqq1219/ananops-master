package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel
@Data
public class MdmcCheckDto implements Serializable {
    @ApiModelProperty("工单编号")
    private Long orderId;

    @ApiModelProperty("审核结果")
    private Boolean approvalResult;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("接单角色")
    private Integer acceptor;//0:服务商；1：维修工

    @ApiModelProperty("接单结果")
    private Boolean acceptResult;

    @ApiModelProperty("确认结果")
    private Boolean confirmResult;


}
