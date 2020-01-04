package com.ananops.provider.model.domain;

import com.ananops.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 业绩
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "an_spc_performance")
public class SpcPerformance extends BaseEntity implements Serializable {

    /**
     * 项目名称
     */
    @Column(name = "project_name")
    private String projectName;

    /**
     * 交易类型
     */
    @Column(name = "project_type")
    private String projectType;

    /**
     * 工程地点
     */
    @Column(name = "project_location")
    private String projectLocation;

    /**
     * 项目负责人姓名
     */
    @Column(name = "project_principal")
    private String projectPrincipal;

    /**
     * 项目负责人身份证号码
     */
    @Column(name = "project_principal_number")
    private String projectPrincipalNumber;

    /**
     * 项目技术负责人姓名
     */
    @Column(name = "project_tech_pr")
    private String projectTechPr;

    /**
     * 项目技术负责人身份证号码
     */
    @Column(name = "project_tech_pr_number")
    private String projectTechPrNumber;

    /**
     * 工程描述
     */
    @Column(name = "project_desc")
    private String projectDesc;

    /**
     * 竣工日期
     */
    @Column(name = "project_finish")
    private Date projectFinish;

    /**
     * 业主名称
     */
    @Column(name = "customer_name")
    private String customerName;

    /**
     * 中标通知书编号
     */
    @Column(name = "bid_number")
    private String bidNumber;

    /**
     * 中标金额（万元）
     */
    @Column(name = "bid_amount")
    private String bidAmount;

    /**
     * 中标通知书url
     */
    @Column(name = "bid_photo")
    private String bidPhoto;

    /**
     * 合同主页url
     */
    @Column(name = "project_contract")
    private String projectContract;

    /**
     * 竣工验收报告url
     */
    @Column(name = "project_finish_report")
    private String projectFinishReport;
}