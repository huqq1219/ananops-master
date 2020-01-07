package com.ananops.provider.model.dto;

import com.ananops.core.mybatis.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 根据工程师状态查询列表
 *
 * Created by bingyueduan on 2020/1/2.
 */
@Data
@ApiModel(value = "工程师状态查询Dto")
public class EngineerStatusDto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8925453118160219628L;

    /**
     * 工程师状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;
}
