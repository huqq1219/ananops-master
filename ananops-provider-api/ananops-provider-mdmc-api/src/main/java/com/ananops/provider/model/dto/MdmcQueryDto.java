package com.ananops.provider.model.dto;

import com.ananops.base.dto.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class MdmcQueryDto extends BaseQuery {

    @ApiModelProperty("角色")
    private String roleCode;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("工单状态")
    private Integer status;

}
