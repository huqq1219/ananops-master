package com.ananops.provider.model.dto;

import com.ananops.core.mybatis.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 根据公司状态查询列表
 *
 * Created by bingyueduan on 2019/12/30.
 */
@Data
@ApiModel(value = "公司状态查询Dto")
public class CompanyStatusDto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -2256607495556581305L;

    /**
     * 公司状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;
}
