package com.ananops.provider.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created By ChengHao On 2019/12/4
 */
@Data
@ApiModel
public class PmcProjectDto implements Serializable {
    private static final long serialVersionUID = -9100877460013166433L;

    @ApiModelProperty(value = "项目id")
    private Long id;

    /**
     * 合同id
     */
    @ApiModelProperty(value = "合同id")
    private Long contractId;

    /**
     * 合同名称
     */
    @ApiModelProperty(value = "合同名称")
    private String contractName;

    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称")
    private String projectName;

    /**
     * 项目类型
     */
    @ApiModelProperty(value = "项目类型")
    private String projectType;

    /**
     * 是否签署合同（0-没有，1-有，没有签署合同的未虚拟项目）
     */
    @ApiModelProperty(value = "是否签署合同（0-没有，1-有，没有签署合同的未虚拟项目）")
    private Integer isContract;

    /**
     * 甲方id
     */
    @ApiModelProperty(value = "甲方id")
    private Long partyAId;

    /**
     * 甲方名称
     */
    @ApiModelProperty(value = "甲方名称")
    private String partyAName;

    /**
     * 乙方id
     */
    @ApiModelProperty(value = "乙方id")
    private Long partyBId;

    /**
     * 乙方名称
     */
    @ApiModelProperty(value = "乙方名称")
    private String partyBName;

    /**
     * 甲方项目负责人id
     */
    @ApiModelProperty(value = "甲方项目负责人id")
    private Long aLeaderId;

    /**
     * 甲方项目负责人姓名
     */
    @ApiModelProperty(value = "甲方项目负责人姓名")
    private String aLeaderName;

    /**
     * 甲方项目负责人电话
     */
    @ApiModelProperty(value = "甲方项目负责人电话")
    private String aLeaderTel;

    /**
     * 甲方联系人1姓名
     */
    @ApiModelProperty(value = "甲方负责人1姓名")
    private String aOneName;

    /**
     * 甲方项目负责人联系方式1
     */
    @ApiModelProperty(value = "甲方项目负责人联系方式1")
    private String partyAOne;

    /**
     * 甲方联系人2姓名
     */
    @ApiModelProperty(value = "甲方联系人2姓名")
    private String aTwoName;

    /**
     * 甲方项目负责人联系方式2
     */
    @ApiModelProperty(value = "甲方负责人2联系方式")
    private String partyATwo;

    /**
     * 甲方联系人3姓名
     */
    @ApiModelProperty(value = "甲方负责人3姓名")
    private String aThreeName;

    /**
     * 甲方项目负责人联系方式3
     */
    @ApiModelProperty(value = "甲方项目负责人3联系方式")
    private String partyAThree;

    /**
     * 乙方项目负责人ID
     */
    @ApiModelProperty(value = "乙方项目负责人ID")
    private Long bLeaderId;

    /**
     * 乙方项目负责人姓名
     */
    @ApiModelProperty(value = "乙方项目负责人姓名")
    private String bLeaderName;

    /**
     * 乙方项目负责人电话
     */
    @ApiModelProperty(value = "乙方项目负责人电话")
    private String bLeaderTel;

    /**
     * 乙方24小时值班电话
     */
    @ApiModelProperty(value = "乙方24小时值班电话")
    private String partyBTel;

    /**
     * 乙方24小时开通的移动电话
     */
    @ApiModelProperty(value = "乙方24小时开通的移动电话")
    private String partyBPhone;

    /**
     * 乙方24小时开通邮箱
     */
    @ApiModelProperty(value = "方24小时开通邮箱")
    private String partyBEmail;

    /**
     * 开始时间（虚拟项目必填）
     */
    @ApiModelProperty(value = "开始时间（虚拟项目必填）",example = "2019-12-01 12:18:48")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 结束时间（虚拟项目必填
     */
    @ApiModelProperty(value = "结束时间（虚拟项目必填)",example = "2019-12-01 12:18:48")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 项目是否作废（0-有效，1-作废）
     */
    @ApiModelProperty(value = "项目是否作废（0-有效，1-作废）")
    private Integer isDestroy;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;
}
