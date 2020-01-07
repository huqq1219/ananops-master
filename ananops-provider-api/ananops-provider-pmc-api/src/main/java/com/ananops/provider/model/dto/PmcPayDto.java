package com.ananops.provider.model.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created By ChengHao On 2019/12/30
 */
@Data
@Api
public class PmcPayDto implements Serializable {
    private static final long serialVersionUID = -7757057432482548163L;

    /**
     * 支付方式（1-现结、2-账期、3-年结）
     */
    @ApiModelProperty(value = "支付方式（1-现结、2-账期、3-年结）")
    private Integer paymentType;

    /**
     * 支付金额
     */
    @ApiModelProperty(value = "支付金额")
    private Long paymentMoney;
}
