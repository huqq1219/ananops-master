package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 修改公司状态Dto
 *
 * Created by bingyueduan on 2019/12/30.
 */
@Data
@ApiModel(value = "公司禁用/激活Dto ")
public class ModifyCompanyStatusDto implements Serializable {

    private static final long serialVersionUID = -7913106103910010484L;

    /**
     * 公司ID
     */
    @ApiModelProperty(value = "公司ID")
    private Long companyId;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String status;
}
