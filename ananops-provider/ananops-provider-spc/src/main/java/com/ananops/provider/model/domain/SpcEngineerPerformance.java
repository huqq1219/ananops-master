package com.ananops.provider.model.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 维修维保工程师与业绩关系
 */
@Data
@Table(name = "an_spc_engineer_performance")
public class SpcEngineerPerformance implements Serializable {

    private static final long serialVersionUID = -2834005787957193437L;

    /**
     * 维修维保工程师ID
     */
    @Column(name = "engineer_id")
    private Long engineerId;

    /**
     * 业绩ID
     */
    @Column(name = "performance_id")
    private Long performanceId;
}