package com.ananops.provider.model.dto;

import com.ananops.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
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