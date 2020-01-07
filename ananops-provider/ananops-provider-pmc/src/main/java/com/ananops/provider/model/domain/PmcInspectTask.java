package com.ananops.provider.model.domain;

import com.ananops.core.mybatis.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "an_pmc_inspect_task")
@Data
@ApiModel
public class PmcInspectTask extends BaseEntity {

    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id")
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称")
    @Column(name = "project_name")
    private String projectName;

    /**
     * 任务名称
     */
    @ApiModelProperty(value = "任务名称")
    @Column(name = "task_name")
    private String taskName;

    /**
     * 任务类型
     */
    @ApiModelProperty(value = "任务类型")
    @Column(name = "task_type")
    private String taskType;

    /**
     * 巡检内容
     */
    @ApiModelProperty(value = "巡检内容")
    @Column(name = "inspection_content")
    private String inspectionContent;

    /**
     * 巡检情况
     */
    @ApiModelProperty(value = "巡检情况")
    @Column(name = "inspection_condition")
    private String inspectionCondition;

    /**
     * 处理结果
     */
    @ApiModelProperty(value = "处理结果")
    @Column(name = "deal_result")
    private String dealResult;

    /**
     * 巡检周期，单位天
     */
    @ApiModelProperty(value = "巡检周期,单位天")
    @Column(name = "cycle_time")
    private Integer cycleTime;

    /**
     * 预计开始时间
     */
    @ApiModelProperty(value = "预计开始时间",example = "2019-12-01 12:18:48")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "scheduled_start_time")
    private Date scheduledStartTime;

    /**
     * 最晚开始时间
     */
    @ApiModelProperty(value = "最晚开始时间",example = "2019-12-01 12:18:48")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "deadline_time")
    private Date deadlineTime;

    /**
     * 计划完成时间，单位天
     */
    @ApiModelProperty(value = "计划完成时间，单位天")
    @Column(name = "scheduled_finish_time")
    private Integer scheduledFinishTime;

    /**
     * 是否立即执行，0-否，1-是
     */
    @ApiModelProperty(value = "是否立即执行，0-否，1-是")

    @Column(name = "is_now")
    private Integer isNow;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

}