package com.ananops.provider.model.dto.group;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 根据组织状态查询列表
 *
 * Created by bingyueduan on 2020/1/2.
 */
@Data
@ApiModel(value = "组织状态查询Dto")
public class GroupStatusDto implements Serializable {

    private static final long serialVersionUID = 6161786844168000613L;

    /**
     * 公司状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 第几页
     */
    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    /**
     * 一页显示项
     */
    @ApiModelProperty(value = "一页显示项")
    private Integer pageSize;

    /**
     * 排序参数
     */
    @ApiModelProperty(value = "排序")
    private String orderBy;
}
