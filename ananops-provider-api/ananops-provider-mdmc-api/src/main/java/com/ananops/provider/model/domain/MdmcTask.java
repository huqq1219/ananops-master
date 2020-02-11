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
@Table(name = "task")
public class MdmcTask extends BaseEntity {

    @Column(name = "object_type")
    private Integer objectType;

    @Column(name = "object_id")
    private Long objectId;

    /**
     * 发起此次维修请求的用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 任务名称
     */
    @Column(name = "title")
    private String title;

    /**
     * 用户负责人（领导）ID
     */
    @Column(name = "principal_id")
    private Long principalId;

    /**
     * 任务对应的项目ID
     */
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 服务商ID
     */
    @Column(name = "facilitator_id")
    private Long facilitatorId;

    /**
     * 维修工ID
     */
    @Column(name = "maintainer_id")
    private Long maintainerId;

    /**
     * 预约维修时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "appoint_time")
    private Date appointTime;

    /**
     * 预计完成时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "scheduled_finish_time")
    private Date scheduledFinishTime;

    /**
     * 实际完成时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "actual_finish_time")
    private Date actualFinishTime;

    /**
     * 预计开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "scheduled_start_time")
    private Date scheduledStartTime;

    /**
     * 实际开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "actual_start_time")
    private Date actualStartTime;

    /**
     * 最迟完成时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deadline;

    /**
     * 请求维修的地点，纬度
     */
    @Column(name = "request_latitude")
    private BigDecimal requestLatitude;

    /**
     * 请求维修的地点，经度
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "request_longitude")
    private BigDecimal requestLongitude;

    /**
     * 当前任务的进度状态
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 维修总花费
     */
    @Column(name = "total_cost")
    private BigDecimal totalCost;

    /**
     * 结算方式
     */
    @Column(name = "clearing_form")
    private Integer clearingForm;

    /**
     * 报修人电话
     */
    @Column(name = "creator_call")
    private String call;

    /**
     * 地址名称
     */
    @Column(name = "address_name")
    private String addressName;

    /**
     * 合同id
     */
    @Column(name = "contract_id")
    private Long contractId;

    /**
     * 紧急程度
     */
    @Column(name = "level")
    private Integer level;

    @Column(name = "suggestion")
    private String suggestion;

    @Column(name = "result")
    private Integer result;

    private String note;

}