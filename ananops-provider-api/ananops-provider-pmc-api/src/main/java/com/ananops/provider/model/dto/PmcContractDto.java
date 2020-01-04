package com.ananops.provider.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created By ChengHao On 2019/12/5
 */
@Data
@ApiModel
public class PmcContractDto implements Serializable {
    private static final long serialVersionUID = -6026304946696283313L;

    @ApiModelProperty(value = "合同id")
    private Long id;
    /**
     * 合同编号
     */
    @ApiModelProperty(value = "合同编号")
    private String contractCode;

    /**
     * 合同名称
     */
    @ApiModelProperty(value = "合同名称")
    private String contractName;

    /**
     * 合同类型
     */
    @ApiModelProperty(value = "合同类型")
    private String contractType;

    /**
     * 甲方id
     */
    @ApiModelProperty(value = "甲方id")
    private Long partyAId;

    /**
     * 甲方组织名称
     */
    @ApiModelProperty(value = "甲方组织名称")
    private String partyAName;

    /**
     * 甲方合同签字法人
     */
    @ApiModelProperty(value = "甲方合同签字法人")
    private String aLegalName;

    /**
     * 乙方id
     */
    @ApiModelProperty(value = "乙方id")
    private Long partyBId;

    /**
     * 乙方组织名称
     */
    @ApiModelProperty(value = "乙方组织名称")
    private String partyBName;

    /**
     * 乙方合同签字法人
     */
    @ApiModelProperty(value = "乙方合同签字法人")
    private String bLegalName;

    /**
     * 乙方开户银行
     */
    @ApiModelProperty(value = "乙方开户银行")
    private String bankName;

    /**
     * 乙方银行账号
     */
    @ApiModelProperty(value = "乙方银行账号")
    private String bankAccount;

    /**
     * 合同签订时间
     */
    @ApiModelProperty(value = "合同签订时间", example = "2019-12-01 12:18:48")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date signTime;

    /**
     * 合同开始时间
     */
    @ApiModelProperty(value = "合同开始时间", example = "2019-12-01 12:18:48")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 合同结束时间
     */
    @ApiModelProperty(value = "合同结束时间", example = "2019-12-01 12:18:48")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 是否自动顺延（0-未顺延，1-顺延）
     */
    @ApiModelProperty(value = "是否自动顺延（0-未顺延，1-顺延）")
    private Integer isPostpone;

    /**
     * 支付方式（1-现结、2-账期、3-年结）
     */
    @ApiModelProperty(value = "支付方式（1-现结、2-账期、3-年结）")
    private Integer paymentType;

    /**
     * 支付金额
     */
    @ApiModelProperty(value = "支付金额")
    private Long paymentMoney;

    /**
     * 项目金额
     */
    @ApiModelProperty(value = "项目金额")
    private Long projectMoney;

    /**
     * 付款时间
     */
    @ApiModelProperty(value = "付款时间", example = "2019-12-01 12:18:48")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date paymentTime;

    /**
     * 维修设备数量
     */
    @ApiModelProperty(value = "维修设备数量")
    private Integer deviceCount;

    /**
     * 乙方代理内容
     */
    @ApiModelProperty(value = "乙方代理内容")
    private String agentContent;

    /**
     * 乙方是否包备品备件（0-不包，1-包）
     */
    @ApiModelProperty(value = "乙方是否包备品备件（0-不包，1-包）")
    private Integer isSparePart;

    /**
     * 乙方是否提供备品备件替换服务（0-不提供，1-提供)
     */
    @ApiModelProperty(value = "乙方是否提供备品备件替换服务（0-不提供，1-提供)")
    private Integer isSpareService;

    /**
     * 乙供辅料金额（乙方会提供一些免费辅件，超过该金额的才会另收费）
     */
    @ApiModelProperty(value = "乙供辅料金额")
    private Long assitMoney;

    /**
     * 维修维护最迟响应时间,单位小时（配合转单功能以及平台的提醒功能，在一定时限内短信、电话或邮件提醒）
     */
    @ApiModelProperty(value = "维修维护最迟响应时间,单位小时")
    private Integer lastResponseTime;

    /**
     * 维修工身份验证流程（对于有保密属性的设备需要验证维修工的身份）
     */
    @ApiModelProperty(value = "维修工身份验证流程")
    private String verification;

    /**
     * 月度记录表提交周期，单位天（也可以为每月第一天）
     */
    @ApiModelProperty(value = "月度记录表提交周期，单位天")
    private Integer recordTime;

    /**
     * 合同是否变更（0-未变更，1-变更）
     */
    @ApiModelProperty(value = "合同是否变更（0-未变更，1-变更）")
    private Integer isChange;

    /**
     * 合同是否作废（0-有效，1-作废）
     */
    @ApiModelProperty(value = "合同是否作废（0-有效，1-作废）")
    private Integer isDestroy;

    /**
     * 附件路径
     */
    @ApiModelProperty(value = "附件路径")
    private String filePath;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;
}
