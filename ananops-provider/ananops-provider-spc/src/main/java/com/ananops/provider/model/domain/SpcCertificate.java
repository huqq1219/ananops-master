package com.ananops.provider.model.domain;

import com.ananops.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 资质证书
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "an_spc_certificate")
public class SpcCertificate extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 5190133424083644465L;

    /**
     * 资格证书编号
     */
    @Column(name = "ce_number")
    private String ceNumber;

    /**
     * 资格证书类型
     */
    @Column(name = "ce_type")
    private String ceType;

    /**
     * 资格证书等级
     */
    @Column(name = "ce_degree")
    private String ceDegree;

    /**
     * 资格证书发放日期
     */
    @Column(name = "ce_first_time")
    private Date ceFirstTime;

    /**
     * 有效期限
     */
    @Column(name = "ce_expiration_date")
    private Date ceExpirationDate;

    /**
     * 发放资格证书单位
     */
    @Column(name = "ce_authority")
    private String ceAuthority;

    /**
     * 资格证书照片url
     */
    @Column(name = "ce_photo")
    private String cePhoto;


}