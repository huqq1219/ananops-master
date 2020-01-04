package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class BillModifyAmountDto implements Serializable {
    @ApiModelProperty(value = "账单id")
    private String id;

    @ApiModelProperty(value = "修改金额")
    private Float modifyAmount;
}
