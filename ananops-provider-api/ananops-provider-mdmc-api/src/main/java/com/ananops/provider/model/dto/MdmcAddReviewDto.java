package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class MdmcAddReviewDto implements Serializable {

    /**
     * 对应的任务ID
     */
    @ApiModelProperty(value = "对应的任务ID")
    private Long taskId;

    /**
     * 项目负责人
     */
    @ApiModelProperty(value = "项目负责人ID")
    private Long principalId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 服务评级
     */
    @ApiModelProperty(value = "服务评级")
    private Integer score;

    /**
     * 服务评论
     */
    @ApiModelProperty(value = "服务评论")
    private String contents;

}
