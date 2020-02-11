package com.ananops.provider.model.vo;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

import java.math.BigDecimal;

@Data
public class DeviceOrderVo {

    @JsonProperty("deviceOrderId")
    private Long id;
    /**
     * 订单绑定对象类型(1.维修工单 2.巡检工单 3.企业订单 4.个人订单)
     */
    private Integer objectType;

    /**
     * 订单绑定对象编号
     */
    private Long objectId;

    /**
     * 订单申请类型
     */
    private Integer type;

    /**
     * 当前备品备件订单的处理状态
     */
    private Integer status;

    /**
     * 状态信息
     */
    private String statusMsg;

    /**
     * 备品备件订单总价
     */
    private BigDecimal totalPrice;

    /**
     * 优惠折扣
     */
    private Float discount;

    /**
     * 订单包含的设备子项 (json)
     */
    private String items;

    /**
     * 备品备件订单处理结果
     */
    private String processResult;

    /**
     * 备品贝吉安订单处理意见
     */
    private String processMsg;
}
