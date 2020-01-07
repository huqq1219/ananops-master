package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 修改工程师状态Dto
 *
 * Created by bingyueduan on 2020/1/2.
 */
@Data
@ApiModel(value = "工程师账号禁用/激活Dto")
public class ModifyEngineerStatusDto implements Serializable {

    private static final long serialVersionUID = -7917712267275377562L;

    /**
     * 工程师ID
     */
    @ApiModelProperty(value = "工程师ID")
    private Long engineerId;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String status;
}
