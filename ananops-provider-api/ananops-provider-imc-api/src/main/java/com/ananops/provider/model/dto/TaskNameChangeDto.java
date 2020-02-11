package com.ananops.provider.model.dto;

import com.ananops.base.dto.LoginAuthDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by rongshuai on 2019/12/13 13:43
 */
@Data
@ApiModel
public class TaskNameChangeDto implements Serializable {
    private static final long serialVersionUID = 575291391637534540L;
    /**
     * 巡检任务ID
     */
    @ApiModelProperty(value = "巡检任务ID")
    private Long taskId;
    /**
     * 修改后的任务名
     */
    @ApiModelProperty(value = "修改后的任务名")
    private String taskName;

    /**
     * 当前操作用户的身份信息
     */
    @ApiModelProperty(value = "当前操作用户的LoginAuthDto")
    private LoginAuthDto loginAuthDto;
}
