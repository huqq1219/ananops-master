package com.ananops.provider.model.vo;

import com.ananops.base.dto.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 加盟服务商查询返回的公司Vo
 *
 * Created by bingyueduan on 2019/12/28.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CompanyVo extends BaseVo {

    private static final long serialVersionUID = -6330126377475850151L;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系人手机号
     */
    private String contactPhone;

    /**
     * 组织编码,统一社会信任代码
     */
    private String groupCode;

    /**
     * 组织名称
     */
    private String groupName;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 组织类型
     */
    private String type;

    /**
     * UAC对应的组织id
     */
    private Long groupId;

    /**
     * UAC用户ID
     */
    private Long userId;

    /**
     * 法人姓名
     */
    private String legalPersonName;

    /**
     * 法人联系方式
     */
    private String legalPersonPhone;

    /**
     * 法人身份证号
     */
    private String legalPersonNumber;

    /**
     * 主体行业
     */
    private String mainWork;

    /**
     * 国家地区
     */
    private String country;

    /**
     * 邮政编码
     */
    private String zipCode;

    /**
     * 基本户账户名称
     */
    private String accountName;

    /**
     * 基本账户账号
     */
    private String accountNumber;

    /**
     * 营业执照类别
     */
    private String licenseType;

    /**
     * 注册资本
     */
    private String registeredCaptial;

    /**
     * 有效期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expirationDate;

    /**
     * 经营范围
     */
    private String businessScope;

    /**
     * 供应产品类别
     */
    private String productCategory;

    /**
     * 法人身份证照片url
     */
    private String legalPersonidPhoto;

    /**
     * 营业执照照片url
     */
    private String businessLicensePhoto;

    /**
     * 账户开户许可证照片url
     */
    private String accountOpeningLicense;

    /**
     * 组织地址
     */
    private String groupAddress;

    /**
     * 省名称
     */
    private String provinceName;

    /**
     * 省编码
     */
    private Long provinceId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 城市编码
     */
    private Long cityId;

    /**
     * 区名称
     */
    private String areaName;

    /**
     * 区编码
     */
    private Long areaId;

    /**
     * 街道编码
     */
    private Long streetId;

    /**
     * 街道名称
     */
    private String streetName;

    /**
     * 详细地址
     */
    private String detailAddress;


}
