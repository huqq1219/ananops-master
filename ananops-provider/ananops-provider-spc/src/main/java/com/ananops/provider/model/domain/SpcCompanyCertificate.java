package com.ananops.provider.model.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 加盟服务商与资质关系
 */
@Data
@Table(name = "an_spc_company_certificate")
public class SpcCompanyCertificate implements Serializable {

    private static final long serialVersionUID = 2487389787731257825L;

    /**
     * 加盟服务商ID
     */
    @Column(name = "company_id")
    private Long companyId;

    /**
     * 资质ID
     */
    @Column(name = "certificate_id")
    private Long certificateId;
}