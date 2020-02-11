package com.ananops.provider.model.dto;

import com.ananops.base.dto.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by rongshuai on 2020/1/3 9:33
 */
@Data
@ApiModel
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class TaskDto extends BaseVo implements Serializable {
    private static final long serialVersionUID = 5222231596006260928L;

    /**
     * 项目负责人ID
     */
    @ApiModelProperty(value = "项目负责人ID")
    private Long principalId;

    /**
     * 服务商ID
     */
    @ApiModelProperty(value = "服务商ID")
    private Long facilitatorId;

    /**
     * 项目ID
     */
    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    /**
     * 巡检位置信息
     */
    @ApiModelProperty(value = "巡检位置信息")
    private String location;

    /**
     * 当前巡检任务进度
     */
    @ApiModelProperty(value = "当前巡检任务进度")
    private Integer status;

    /**
     * 本次巡检总花费
     */
    @ApiModelProperty(value = "本次巡检总花费")
    private BigDecimal totalCost;

    /**
     * 巡检产生的维修维护费用
     */
    @ApiModelProperty(value = "巡检产生的维修维护费用")
    private BigDecimal maintenanceCost;

    /**
     * 计划起始时间
     */
    @ApiModelProperty(value = "计划起始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date scheduledStartTime;

    /**
     * 实际完成时间
     */
    @ApiModelProperty(value = "实际完成时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date actualFinishTime;

    /**
     * 计划完成天数
     */
    @ApiModelProperty(value = "计划完成天数")
    private Integer days;

    /**
     * 巡检类型（1.按合同产生的巡检，2.甲方负责人主动发出的巡检）
     */
    @ApiModelProperty(value = "巡检类型（1.按合同产生的巡检，2.甲方负责人主动发出的巡检）")
    private Integer inspectionType;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 巡检任务名称
     */
    @ApiModelProperty(value = "巡检任务名称")
    private String taskName;

    /**
     * 巡检周期（月）
     */
    @ApiModelProperty(value = "巡检周期（月）")
    private Integer frequency;

    /**
     * 巡检任务对应的巡检任务子项列表
     */
    @ApiModelProperty(value = "巡检任务对应的巡检任务子项列表")
    private List<ItemDto> itemDtoList;
}
