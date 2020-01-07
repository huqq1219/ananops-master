package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/*工单的处理进度列表*/
@Data
@ApiModel
public class MdmcProcessingDto implements Serializable {

    @ApiModelProperty("工单状态")
    private Integer status;

    @ApiModelProperty("操作人")
    private String last_operator;

    @ApiModelProperty("操作时间")
    private Date status_timestamp;

    @ApiModelProperty("当前操作的描述")
    private String movement;
}
