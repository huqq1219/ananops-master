package com.ananops.provider.model.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created By ChengHao On 2019/12/30
 */
@Data
@Api
public class PmcBatchProUser implements Serializable {

    private static final long serialVersionUID = -8138406365457331646L;

    @ApiModelProperty(value = "项目用户关系表")
    List<PmcProjectUserDto> pmcProjectUserDtoList;
}
