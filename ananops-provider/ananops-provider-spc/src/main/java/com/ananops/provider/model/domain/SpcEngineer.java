package com.ananops.provider.model.domain;

import com.ananops.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 维修维保工程师
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "an_spc_engineer")
public class SpcEngineer extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -4143344904810486260L;

    /**
     * UAC对应的用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 性别
     */
    private String sex;

    /**
     * 所在省市
     */
    private String location;

    /**
     * 身份证号码
     */
    @Column(name = "identity_number")
    private String identityNumber;

    /**
     * 身份证有效期
     */
    @Column(name = "identity_expiration_date")
    private Date identityExpirationDate;

    /**
     * 职务
     */
    private String position;

    /**
     * 职称
     */
    private String title;

    /**
     * 学历
     */
    private String education;

    /**
     * 从业开始时间
     */
    @Column(name = "employment_start_time")
    private Date employmentStartTime;

    /**
     * 工程师身份证照片url
     */
    @Column(name = "identity_photo")
    private String identityPhoto;

    /**
     * 职称证书编号
     */
    @Column(name = "title_ce_number")
    private String titleCeNumber;

    /**
     * 职称证书发放日期
     */
    @Column(name = "title_ce_time")
    private Date titleCeTime;

    /**
     * 发放职称证书单位
     */
    @Column(name = "title_ce_authority")
    private String titleCeAuthority;

    /**
     * 职称证书照片url
     */
    @Column(name = "title_ce_photo")
    private String titleCePhoto;
}