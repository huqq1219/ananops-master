package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Transient;

/**
 * 按状态，类型查询工单Dto
 */
@Data
@ApiModel(value = "工单详情查询Dto")
public class WorkOrderStatusQueryDto {

    @Transient
    private Integer pageNum;

    @Transient
    private Integer pageSize;

    @Transient
    private String orderBy;

    /**
     * 工单类型; 巡检(inspection)和维修维护(maintain)
     */
    private String type;

    /**
     * 工单状态; 待处理/已分配/进行中
     */
    private Integer status;
}
