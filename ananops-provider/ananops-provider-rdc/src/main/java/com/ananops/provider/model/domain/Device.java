package com.ananops.provider.model.domain;

import com.ananops.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "an_rdc_device")
public class Device extends BaseEntity {
    
    private static final long serialVersionUID = -5711468008814085690L;
    
    /**
     * 设备名称
     */
    private String name;
    
    /**
     * 设备单价
     */
    private BigDecimal price;

    /**
     * 设备类型
     */
    private String type;

    /**
     * 设备生产商
     */
    private String manufacture;

    /**
     * 设备型号
     */
    private String model;

    /**
     * 设备库存
     */
    private Integer store;
    
}