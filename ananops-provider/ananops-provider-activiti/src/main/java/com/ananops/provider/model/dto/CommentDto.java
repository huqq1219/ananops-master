package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class CommentDto implements Serializable {
    @ApiModelProperty(value = "批注人")
    private String assignee;

    @ApiModelProperty(value = "批注")
    private String comment;

}
