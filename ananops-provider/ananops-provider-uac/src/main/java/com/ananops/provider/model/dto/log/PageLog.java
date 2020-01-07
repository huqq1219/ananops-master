package com.ananops.provider.model.dto.log;

import com.ananops.base.dto.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class PageLog extends BaseQuery {

    @ApiModelProperty(value = "用户Id")
    private Long userId;
}
