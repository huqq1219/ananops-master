package com.ananops.provider.model.dto;

import com.ananops.base.dto.LoginAuthDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel
public class MdmcChangeStatusDto implements Serializable {

    /**
     * 巡检任务ID
     */
    @ApiModelProperty(value = "任务ID")
    private Long taskId;

    /**
     * 修改后的状态
     */
    @ApiModelProperty(value = "修改后的状态")
    private Integer status;

    /**
     * 任务修改后的状态描述
     */
    @ApiModelProperty(value = "任务修改后的状态描述")
    private String statusMsg;

    /**
     * 当前操作用户的身份信息
     */
    @ApiModelProperty(value = "当前操作用户的LoginAuthDto")
    private LoginAuthDto loginAuthDto;
}
