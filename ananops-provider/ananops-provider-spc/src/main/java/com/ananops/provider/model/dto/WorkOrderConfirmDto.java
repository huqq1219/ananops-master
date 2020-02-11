package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by rongshuai on 2020/1/10 10:22
 */
@Data
@ApiModel(value = "工单审批Dto")
public class WorkOrderConfirmDto implements Serializable {
    private static final long serialVersionUID = 6562196466359815903L;

    @ApiModelProperty(value = "工单详情查询Dto")
    private WorkOrderQueryDto workOrderQueryDto;

    @ApiModelProperty(value = "工单审批结果（0：否决，1：同意）")
    private Integer decision;
}
