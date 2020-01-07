package com.ananops.provider.model.dto;

import com.ananops.base.dto.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class MdmcStatusDto extends BaseQuery {

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("工单id")
    private Long taskId;



}
