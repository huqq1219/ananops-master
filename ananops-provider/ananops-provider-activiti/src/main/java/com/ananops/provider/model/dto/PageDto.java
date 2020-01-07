package com.ananops.provider.model.dto;

import com.ananops.provider.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel
@Data
public class PageDto implements Serializable {
    @ApiModelProperty(value = "当前用户id")
    private Long userid;

    @ApiModelProperty(value = "起始页面")
    private int pageNum;

    @ApiModelProperty(value = "页面大小")
    private int pageSize;
}
