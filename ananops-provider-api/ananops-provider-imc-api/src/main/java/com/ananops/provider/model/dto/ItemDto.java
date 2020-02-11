package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by rongshuai on 2019/12/18 11:03
 */
@Data
@ApiModel
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ItemDto implements Serializable {
    private static final long serialVersionUID = -1185696597778126041L;
    /**
     *
     */
    @ApiModelProperty(value = "ID")
    private Long id;
    /**
     * 从属的巡检任务的ID
     */
    @ApiModelProperty(value = "从属的巡检任务的ID")
    private Long inspectionTaskId;

    /**
     * 计划开始时间
     */
    @ApiModelProperty(value = "计划开始时间")
    private Date scheduledStartTime;

    /**
     * 实际开始时间
     */
    @ApiModelProperty(value = "实际开始时间")
    private Date actualStartTime;

    /**
     * 实际完成时间
     */
    @ApiModelProperty(value = "实际完成时间")
    private Date actualFinishTime;

    /**
     * 计划完成天数
     */
    @ApiModelProperty(value = "计划完成天数")
    private Integer days;

    /**
     * 巡检周期（天）
     */
    @ApiModelProperty(value = "巡检周期（天）")
    private Integer frequency;

    /**
     * 巡检子项内容描述
     */
    @ApiModelProperty(value = "巡检子项内容描述")
    private String description;

    /**
     * 巡检子项的巡检状态
     */
    @ApiModelProperty(value = "巡检子项的巡检状态")
    private Integer status;

    /**
     * 巡检子项的位置，纬度
     */
    @ApiModelProperty(value = "巡检子项的位置，纬度")
    private BigDecimal itemLatitude;

    /**
     * 巡检子项的位置，经度
     */
    @ApiModelProperty(value = "巡检子项的位置，经度")
    private BigDecimal itemLongitude;

    /**
     * 巡检子项结果描述
     */
    @ApiModelProperty(value = "巡检子项结果描述")
    private String result;

    /**
     * 巡检子项的名称
     */
    @ApiModelProperty(value = "巡检子项的名称")
    private String itemName;

    /**
     * 巡检子项对应的维修工
     */
    @ApiModelProperty(value = "巡检子项对应的维修工")
    private Long maintainerId;

    /**
     * 巡检任务子项已经执行的次数
     */
    @ApiModelProperty(value = "巡检任务子项已经执行的次数")
    private Integer count;
}
