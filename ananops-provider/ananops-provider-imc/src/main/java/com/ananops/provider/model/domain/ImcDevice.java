package com.ananops.provider.model.domain;

import com.ananops.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "an_imc_device")
public class ImcDevice extends BaseEntity {
    private static final long serialVersionUID = -266164296926669966L;

    /**
     * 设备的ID
     */
    @Column(name = "device_id")
    private Long deviceId;

    /**
     * 当前订单花费
     */
    private BigDecimal cost;

    /**
     * 设备类型
     */
    @Column(name = "device_type")
    private String deviceType;

    /**
     * 设别生产商
     */
    private String manufacture;

    /**
     * 设备型号
     */
    @Column(name = "device_model")
    private String deviceModel;

    /**
     * 设备对应的订单ID
     */
    @Column(name = "order_id")
    private Long orderId;

}