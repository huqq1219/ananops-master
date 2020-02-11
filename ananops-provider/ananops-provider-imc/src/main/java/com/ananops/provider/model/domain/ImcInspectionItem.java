package com.ananops.provider.model.domain;

import com.ananops.core.mybatis.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "an_imc_inspection_item")
public class ImcInspectionItem extends BaseEntity {
    private static final long serialVersionUID = -1997464510426371277L;
    /**
     * 从属的巡检任务的ID
     */
    @Column(name = "inspection_task_id")
    private Long inspectionTaskId;

    /**
     * 计划开始时间
     */
    @Column(name = "scheduled_start_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date scheduledStartTime;

    /**
     * 实际开始时间
     */
    @Column(name = "actual_start_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date actualStartTime;

    /**
     * 实际完成时间
     */
    @Column(name = "actual_finish_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date actualFinishTime;

    /**
     * 计划完成天数
     */
    private Integer days;

    /**
     * 巡检周期（月）
     */
    private Integer frequency;

    /**
     * 巡检子项内容描述
     */
    private String description;

    /**
     * 巡检子项的巡检状态
     */
    private Integer status;

    /**
     * 巡检子项的位置，纬度
     */
    @Column(name = "item_latitude")
    private BigDecimal itemLatitude;

    /**
     * 巡检子项的位置，经度
     */
    @Column(name = "item_longitude")
    private BigDecimal itemLongitude;

    /**
     * 巡检子项结果描述
     */
    private String result;

    /**
     * 巡检子项的名称
     */
    @Column(name = "item_name")
    private String itemName;

    /**
     * 巡检子项对应的维修工
     */
    @Column(name = "maintainer_id")
    private Long maintainerId;

    /**
     * 巡检任务子项已经执行的次数
     */
    private Integer count;

    /**
     * 巡检任务子项的网点地址
     */
    private String location;
}