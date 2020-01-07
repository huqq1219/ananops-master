package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel
public class MdmcResultDto implements Serializable {
    @ApiModelProperty("工单编号")
    private Long task_id;

    @ApiModelProperty("备件名称")
    private String device_name;

    @ApiModelProperty("备件编号")
    private Long device_id;

    @ApiModelProperty("备件数量")
    private Integer count;

    @ApiModelProperty("备件单价")
    private BigDecimal cost;

    @ApiModelProperty("报修人")
    private Long user_id;

    @ApiModelProperty("开始时间")
    private Date actual_start_time;

    @ApiModelProperty("结束时间")
    private Date actual_finish_time;

    @ApiModelProperty("总花费")
    private BigDecimal totalcost;

    @ApiModelProperty("工单状态")
    private Integer status;

    @ApiModelProperty("维修工id")
    private Long maintainer_id;
}
