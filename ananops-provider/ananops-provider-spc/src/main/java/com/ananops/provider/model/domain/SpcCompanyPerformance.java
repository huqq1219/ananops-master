package com.ananops.provider.model.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 加盟服务商与业绩关系
 */
@Data
@Table(name = "an_spc_company_performance")
public class SpcCompanyPerformance implements Serializable {

    private static final long serialVersionUID = -3044619895000512600L;

    /**
     * 加盟服务商ID
     */
    @Column(name = "company_id")
    private Long companyId;

    /**
     * 业绩ID
     */
    @Column(name = "performance_id")
    private Long performanceId;
}