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
@Table(name = "task_item")
public class MdmcTaskItem extends BaseEntity {
    private static final long serialVersionUID = 8604444543573834036L;
    /**
     * 任务ID
     */
    @Column(name = "task_id")
    private Long taskId;

    /**
     * 设备ID
     */
    @Column(name = "device_id")
    private Long deviceId;

    /**
     * 设备名称
     */
    @Column(name = "device_name")
    private String deviceName;

    /**
     * 完成维修的时间戳
     */
    @Column(name = "actual_finish_time")
    private Date actualFinishTime;

    /**
     * 开始维修的时间戳
     */
    @Column(name = "actual_start_time")
    private Date actualStartTime;

    /**
     * 故障描述
     */
    private String description;

    /**
     * 故障等级
     */
    private Integer level;

    /**
     * 故障设备位置，纬度
     */
    @Column(name = "device_latitude")
    private BigDecimal deviceLatitude;

    /**
     * 故障设备位置，经度
     */
    @Column(name = "device_longitude")
    private BigDecimal deviceLongitude;

    /**
     * 维修工ID
     */
    @Column(name = "maintainer_id")
    private Long maintainerId;

    /**
     * 当前维修状态
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 维修建议（维修工填写）
     */
    @Column(name = "suggestion")
    private String suggestion;

    /**
     * 维修结果（维修工填写）
     */
    @Column(name = "result")
    private String result;

    /**
     * 图片url
     */
    @Column(name = "photo_url")
    private String photo_url;

    /**
     * 故障类型
     */
    @Column(name = "trouble_type")
    private Integer troubleType;

}