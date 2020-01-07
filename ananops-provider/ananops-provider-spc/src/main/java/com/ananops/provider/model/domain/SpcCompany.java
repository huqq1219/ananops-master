package com.ananops.provider.model.domain;

import com.ananops.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 加盟服务商
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "an_spc_company")
public class SpcCompany extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 2528719818301957156L;

    /**
     * UAC对应的组织id
     */
    @Column(name = "group_id")
    private Long groupId;

    /**
     * UAC用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 法人姓名
     */
    @Column(name = "legal_person_name")
    private String legalPersonName;

    /**
     * 法人联系方式
     */
    @Column(name = "legal_person_phone")
    private String legalPersonPhone;

    /**
     * 法人身份证号
     */
    @Column(name = "legal_person_number")
    private String legalPersonNumber;

    /**
     * 主体行业
     */
    @Column(name = "main_work")
    private String mainWork;

    /**
     * 国家地区
     */
    private String country;

    /**
     * 邮政编码
     */
    @Column(name = "zip_code")
    private String zipCode;

    /**
     * 基本户账户名称
     */
    @Column(name = "account_name")
    private String accountName;

    /**
     * 基本账户账号
     */
    @Column(name = "account_number")
    private String accountNumber;

    /**
     * 营业执照类别
     */
    @Column(name = "license_type")
    private String licenseType;

    /**
     * 注册资本
     */
    @Column(name = "registered_captial")
    private String registeredCaptial;

    /**
     * 有效期
     */
    @Column(name = "expiration_date")
    private Date expirationDate;

    /**
     * 经营范围
     */
    @Column(name = "business_scope")
    private String businessScope;

    /**
     * 供应产品类别
     */
    @Column(name = "product_category")
    private String productCategory;

    /**
     * 法人身份证照片url
     */
    @Column(name = "legal_personId_photo")
    private String legalPersonidPhoto;

    /**
     * 营业执照照片url
     */
    @Column(name = "business_license_photo")
    private String businessLicensePhoto;

    /**
     * 账户开户许可证照片url
     */
    @Column(name = "account_opening_license")
    private String accountOpeningLicense;
}