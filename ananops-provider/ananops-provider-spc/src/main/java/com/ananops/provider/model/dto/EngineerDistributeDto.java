package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by rongshuai on 2020/1/8 17:31
 */
@Data
@ApiModel(value = "工程师分配Dto（输入工程师的Id以及维修维护任务的Id或是巡检任务子项的Id）")
public class EngineerDistributeDto implements Serializable {
    private static final long serialVersionUID = 4396949040228384622L;

    /**
     * 维修维护任务的Id或是巡检任务子项的Id
     */
    @ApiModelProperty(value = "维修维护任务的Id或是巡检任务子项的Id")
    private Long taskId;

    @ApiModelProperty(value = "工程师Id")
    private Long engineerId;
}
