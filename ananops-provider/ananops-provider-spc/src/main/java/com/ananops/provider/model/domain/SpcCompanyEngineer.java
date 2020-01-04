package com.ananops.provider.model.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 加盟服务商与工程师关系
 */
@Data
@Table(name = "an_spc_company_engineer")
public class SpcCompanyEngineer implements Serializable {

    private static final long serialVersionUID = 6456186012751501998L;

    /**
     * 加盟服务商ID
     */
    @Column(name = "company_id")
    private Long companyId;

    /**
     * 工程师ID
     */
    @Column(name = "engineer_id")
    private Long engineerId;
}