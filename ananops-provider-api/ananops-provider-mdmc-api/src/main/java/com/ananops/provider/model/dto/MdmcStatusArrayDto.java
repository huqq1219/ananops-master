package com.ananops.provider.model.dto;

import com.ananops.base.dto.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class MdmcStatusArrayDto extends BaseQuery {
    @ApiModelProperty("状态")
    private Integer[] status;

    @ApiModelProperty("角色")
    private String roleCode;

    @ApiModelProperty("id")
    private Long id;


}
