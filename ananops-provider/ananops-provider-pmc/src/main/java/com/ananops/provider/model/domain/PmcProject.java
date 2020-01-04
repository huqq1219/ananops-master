package com.ananops.provider.model.domain;

import com.ananops.core.mybatis.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "an_pmc_project")
@Data
@ApiModel
public class PmcProject extends BaseEntity {

    /**
     * 合同id
     */
    @ApiModelProperty(value = "合同id")
    @Column(name = "contract_id")
    private Long contractId;

    /**
     * 合同名称
     */
    @ApiModelProperty(value = "合同名称")
    @Column(name = "contract_name")
    private String contractName;

    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称")
    @Column(name = "project_name")
    private String projectName;

    /**
     * 项目类型
     */
    @ApiModelProperty(value = "项目类型")
    @Column(name = "project_type")
    private String projectType;

    /**
     * 是否签署合同（0-没有，1-有，没有签署合同的未虚拟项目）
     */
    @ApiModelProperty(value = "是否签署合同（0-没有，1-有，没有签署合同的未虚拟项目）")
    @Column(name = "is_contract")
    private Integer isContract;

    /**
     * 甲方id
     */
    @ApiModelProperty(value = "甲方id")
    @Column(name = "party_a_id")
    private Long partyAId;

    /**
     * 甲方名称
     */
    @ApiModelProperty(value = "甲方名称")
    @Column(name = "party_a_name")
    private String partyAName;

    /**
     * 乙方id
     */
    @ApiModelProperty(value = "乙方id")
    @Column(name = "party_b_id")
    private Long partyBId;

    /**
     * 乙方名称
     */
    @ApiModelProperty(value = "乙方名称")
    @Column(name = "party_b_name")
    private String partyBName;

    /**
     * 甲方项目负责人id
     */
    @ApiModelProperty(value = "甲方项目负责人id")
    @Column(name = "a_leader_id")
    private Long aLeaderId;

    /**
     * 甲方项目负责人姓名
     */
    @ApiModelProperty(value = "甲方项目负责人姓名")
    @Column(name = "a_leader_name")
    private String aLeaderName;

    /**
     * 甲方项目负责人电话
     */
    @ApiModelProperty(value = "甲方项目负责人电话")
    @Column(name = "a_leader_tel")
    private String aLeaderTel;

    /**
     * 甲方联系人1姓名
     */
    @ApiModelProperty(value = "甲方负责人1姓名")
    @Column(name = "a_one_name")
    private String aOneName;

    /**
     * 甲方项目负责人联系方式1
     */
    @ApiModelProperty(value = "甲方项目负责人联系方式1")
    @Column(name = "party_a_one")
    private String partyAOne;

    /**
     * 甲方联系人2姓名
     */
    @ApiModelProperty(value = "甲方联系人2姓名")
    @Column(name = "a_two_name")
    private String aTwoName;

    /**
     * 甲方项目负责人联系方式2
     */
    @ApiModelProperty(value = "甲方负责人2联系方式")
    @Column(name = "party_a_two")
    private String partyATwo;

    /**
     * 甲方联系人3姓名
     */
    @ApiModelProperty(value = "甲方负责人3姓名")
    @Column(name = "a_three_name")
    private String aThreeName;

    /**
     * 甲方项目负责人联系方式3
     */
    @ApiModelProperty(value = "甲方项目负责人3联系方式")
    @Column(name = "party_a_three")
    private String partyAThree;

    /**
     * 乙方项目负责人ID
     */
    @ApiModelProperty(value = "乙方项目负责人ID")
    @Column(name = "b_leader_id")
    private Long bLeaderId;

    /**
     * 乙方项目负责人姓名
     */
    @ApiModelProperty(value = "乙方项目负责人姓名")
    @Column(name = "b_leader_name")
    private String bLeaderName;

    /**
     * 乙方项目负责人电话
     */
    @ApiModelProperty(value = "乙方项目负责人电话")
    @Column(name = "b_leader_tel")
    private String bLeaderTel;

    /**
     * 乙方24小时值班电话
     */
    @ApiModelProperty(value = "乙方24小时值班电话")
    @Column(name = "party_b_tel")
    private String partyBTel;

    /**
     * 乙方24小时开通的移动电话
     */
    @ApiModelProperty(value = "乙方24小时开通的移动电话")
    @Column(name = "party_b_phone")
    private String partyBPhone;

    /**
     * 乙方24小时开通邮箱
     */
    @ApiModelProperty(value = "方24小时开通邮箱")
    @Column(name = "party_b_email")
    private String partyBEmail;

    /**
     * 开始时间（虚拟项目必填）
     */
    @ApiModelProperty(value = "开始时间（虚拟项目必填）",example = "2019-12-01 12:18:48")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 结束时间（虚拟项目必填
     */
    @ApiModelProperty(value = "结束时间（虚拟项目必填)",example = "2019-12-01 12:18:48")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 项目是否作废（0-有效，1-作废）
     */
    @ApiModelProperty(value = "项目是否作废（0-有效，1-作废）")
    @Column(name = "is_destroy")
    private Integer isDestroy;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

}