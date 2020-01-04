package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * Created By ChengHao On 2019/12/23
 */
@Data
@ApiModel
public class PmcInspectDetailDto implements Serializable {
    private static final long serialVersionUID = -8846828692852324246L;

    @ApiModelProperty(value = "巡检详情id")
    private Long id;
    /**
     * 名称
     */
    @ApiModelProperty(value = "巡检详情名称")
    private String name;

    /**
     * 巡检任务ID
     */
    @ApiModelProperty(value = "巡检任务ID")
    private Long inspectionTaskId;

    /**
     * 巡检任务名称
     */
    @ApiModelProperty(value = "巡检任务名称")
    private String inspectionTaskName;

    /**
     * 巡检网点
     */
    @ApiModelProperty(value = "巡检网点")
    private String itemName;

    /**
     * 维修工id
     */
    @ApiModelProperty(value = "维修工id")
    private Long maintainerId;

    /**
     * 维修工名称
     */
    @ApiModelProperty(value = "维修工名称")
    private String maintainerName;

    /**
     * 巡检内容描述
     */
    @ApiModelProperty(value = "巡检内容描述")
    private String description;

    /**
     * 巡检结果
     */
    @ApiModelProperty(value = "巡检结果")
    private String result;

    /**
     * 巡检状态
     */
    @ApiModelProperty(value = "巡检状态")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}
