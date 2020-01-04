package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created By ChengHao On 2019/12/30
 */
@Data
@ApiModel
public class PmcProjectUserDto implements Serializable {
    private static final long serialVersionUID = -5852340029601394123L;

    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id")
    private Long projectId;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;
}
