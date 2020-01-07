package com.ananops.provider.model.domain;


import com.ananops.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "device_order")
public class MdmcDeviceOrder extends BaseEntity {
    private static final long serialVersionUID = 5974877933815704041L;
    /**
     * 对应的任务ID
     */
    @Column(name = "task_id")
    private Long taskId;


    /**
     * 用户负责人（领导）ID
     */
    @Column(name = "principal_id")
    private Long principalId;

    @Column(name = "maintainer_id")
    private Long maintainerId;

    /**
     * 备件订单的花费
     */
    @Column(name = "cost")
    private BigDecimal cost;

}