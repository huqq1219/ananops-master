package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/*修改工单内容*/
@Data
@ApiModel
public class MdmcUpdateTaskDto {
    @ApiModelProperty("工单编号")
    private Long orderId;

    @ApiModelProperty("工单状态")
    private Integer status;

    @ApiModelProperty("工单总价")
    private BigDecimal total_cost;

}
