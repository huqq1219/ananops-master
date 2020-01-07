package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询工单详情Dto
 */
@Data
@ApiModel(value = "工单详情查询Dto")
public class WorkOrderQueryDto implements Serializable {

    private static final long serialVersionUID = -7789854474719943806L;

    /**
     * 工单Id
     */
    private Long id;

    /**
     * 工单类型; 巡检(inspection)和维修维护(maintain)
     */
    private String type;
}
