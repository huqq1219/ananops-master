package com.ananops.provider.model.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 维修维保工程师与资质证书关系
 */
@Data
@Table(name = "an_spc_engineer_certificate")
public class SpcEngineerCertificate implements Serializable {

    private static final long serialVersionUID = -8203598951994449032L;

    /**
     * 工程师ID
     */
    @Column(name = "engineer_id")
    private Long engineerId;

    /**
     * 资质证书ID
     */
    @Column(name = "certificate_id")
    private Long certificateId;
}