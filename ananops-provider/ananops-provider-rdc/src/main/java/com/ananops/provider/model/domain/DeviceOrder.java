package com.ananops.provider.model.domain;

import com.ananops.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "an_rdc_order")
public class DeviceOrder extends BaseEntity implements Serializable {
    
    /**
     * 订单绑定对象类型(1.维修工单 2.巡检工单 3.企业订单 4.个人订单)
     */
    @Column(name = "object_type")
    private Integer objectType;

    /**
     * 订单绑定对象编号
     */
    @Column(name = "object_id")
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
    @Column(name = "status_msg")
    private String statusMsg;

    /**
     * 备品备件订单总价
     */
    @Column(name = "total_price")
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
    
    /**
     * 报价信息
     */
    private String quotationText;
    
    /**
     * 报价表下载链接
     */
    private String quotationUrl;
    
}