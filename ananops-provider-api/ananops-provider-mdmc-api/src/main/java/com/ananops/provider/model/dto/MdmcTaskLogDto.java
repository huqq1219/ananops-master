package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ApiModel
public class MdmcTaskLogDto implements Serializable {
    @ApiModelProperty("工单编号")
    private  Long taskId;

    @ApiModelProperty("任务日志id")
    private Long taskLogId;

    @ApiModelProperty("工单状态")
    private Integer status;

    @ApiModelProperty("操作人")
    private String last_operator;

    @ApiModelProperty("操作时间")
    private Date status_timestamp;

    @ApiModelProperty("当前操作的描述")
    private String movement;

    @ApiModelProperty("日志任务子项")
    private List<MdmcTaskItemLogDto> taskItemLogDtoList;
}
