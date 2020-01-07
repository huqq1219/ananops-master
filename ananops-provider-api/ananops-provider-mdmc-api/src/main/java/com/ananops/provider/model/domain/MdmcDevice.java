package com.ananops.provider.model.domain;

import com.ananops.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "device")
public class MdmcDevice extends BaseEntity {
    private static final long serialVersionUID = 8604444743573834036L;
    /**
     * 设备型号
     */
    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "task_item_id")
    private Long taskItemId;

    @Column(name = "maintainer_id")
    private Long maintainerId;

    @Column(name = "principal_id")
    private Long principalId;

    @Column(name = "device_name")
    private String device_name;

    /**
     * 设备类型
     */
    @Column(name = "device_type")
    private String deviceType;

    /**
     * 剩余设备数
     */
    @Column(name = "count")
    private Integer count;

    /**
     * 设备单价
     */
    @Column(name = "cost")
    private BigDecimal cost;

    /**
     * 设备生产厂商
     */
    private String manufacture;

    /**
     * 设备生产日期
     */
    @Column(name = "production_date")
    private Date productionDate;

    /**
     * 设备安装日期
     */
    @Column(name = "installation_date")
    private Date installationDate;




}