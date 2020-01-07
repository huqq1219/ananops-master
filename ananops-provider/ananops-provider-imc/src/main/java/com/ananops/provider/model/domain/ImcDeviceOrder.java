package com.ananops.provider.model.domain;

import com.ananops.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "an_imc_device_order")
public class ImcDeviceOrder extends BaseEntity {
    private static final long serialVersionUID = -1894808200318168653L;
    /**
     * 对应的巡检子项目的ID
     */
    @Column(name = "inspection_item_id")
    private Long inspectionItemId;

    /**
     * 对应的巡检任务的ID
     */
    @Column(name = "inspection_task_id")
    private Long inspectionTaskId;

    /**
     * 当前备品备件订单的处理状态
     */
    private Integer status;

    /**
     * 对该订单的处理意见
     */
    private String comment;
}