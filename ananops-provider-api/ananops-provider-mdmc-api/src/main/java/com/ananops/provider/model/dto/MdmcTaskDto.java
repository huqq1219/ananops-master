package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel
public class MdmcTaskDto implements Serializable {

    @ApiModelProperty("维修任务ID")
    private Long id;

    @ApiModelProperty("维修任务名称")
    private String title;

    @ApiModelProperty("当前花费")
    private BigDecimal totalCost;

    @ApiModelProperty("紧急程度")
    private Integer level;

    @ApiModelProperty("维修结果")
    private Integer result;

    @ApiModelProperty("维修建议")
    private String suggestion;
}
