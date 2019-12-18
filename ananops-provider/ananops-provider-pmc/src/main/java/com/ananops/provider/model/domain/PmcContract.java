package com.ananops.provider.model.domain;

import com.ananops.core.mybatis.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "an_pmc_contract")
@Data
public class PmcContract extends BaseEntity {
    /**
     * 合同编号
     */
    @Column(name = "contract_code")
    private String contractCode;

    /**
     * 合同名称
     */
    @Column(name = "contract_name")
    private String contractName;

    /**
     * 合同类型
     */
    @Column(name = "contract_type")
    private String contractType;

    /**
     * 甲方id
     */
    @Column(name = "party_a_id")
    private Long partyAId;

    /**
     * 甲方组织名称
     */
    @Column(name = "party_a_name")
    private String partyAName;

    /**
     * 甲方合同签字法人
     */
    @Column(name = "a_legal_name")
    private String aLegalName;

    /**
     * 乙方id
     */
    @Column(name = "party_b_id")
    private Long partyBId;

    /**
     * 乙方组织名称
     */
    @Column(name = "party_b_name")
    private String partyBName;

    /**
     * 乙方合同签字法人
     */
    @Column(name = "b_legal_name")
    private String bLegalName;

    /**
     * 乙方开户银行
     */
    @Column(name = "bank_name")
    private String bankName;

    /**
     * 乙方银行账号
     */
    @Column(name = "bank_account")
    private String bankAccount;

    /**
     * 合同签订时间
     */
    @Column(name = "sign_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date signTime;

    /**
     * 合同开始时间
     */
    @Column(name = "start_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 合同结束时间
     */
    @Column(name = "end_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 是否自动顺延（0-未顺延，1-顺延）
     */
    @Column(name = "is_postpone")
    private Integer isPostpone;

    /**
     * 支付方式（1-现结、2-账期、3-年结）
     */
    @Column(name = "payment_type")
    private Integer paymentType;

    /**
     * 项目金额
     */
    @Column(name = "project_money")
    private Long projectMoney;

    /**
     * 付款时间
     */
    @Column(name = "payment_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date paymentTime;

    /**
     * 维修设备数量
     */
    @Column(name = "device_count")
    private Integer deviceCount;

    /**
     * 乙方代理内容
     */
    @Column(name = "agent_content")
    private String agentContent;

    /**
     * 乙方是否包备品备件（0-不包，1-包）
     */
    @Column(name = "is_spare_part")
    private Integer isSparePart;

    /**
     * 乙方是否提供备品备件替换服务（0-不提供，1-提供)
     */
    @Column(name = "is_spare_service")
    private Integer isSpareService;

    /**
     * 乙供辅料金额（乙方会提供一些免费辅件，超过该金额的才会另收费）
     */
    @Column(name = "assit_money")
    private Long assitMoney;

    /**
     * 维修维护最迟响应时间,单位小时（配合转单功能以及平台的提醒功能，在一定时限内短信、电话或邮件提醒）
     */
    @Column(name = "last_response_time")
    private Integer lastResponseTime;

    /**
     * 维修工身份验证流程（对于有保密属性的设备需要验证维修工的身份）
     */
    private String verification;

    /**
     * 月度记录表提交周期，单位天（也可以为每月第一天）
     */
    @Column(name = "record_time")
    private Integer recordTime;

    /**
     * 合同是否变更（0-未变更，1-变更）
     */
    @Column(name = "is_change")
    private Integer isChange;

    /**
     * 合同是否作废（0-有效，1-作废）
     */
    @Column(name = "is_destory")
    private Integer isDestory;

    /**
     * 附件路径
     */
    @Column(name = "file_path")
    private String filePath;

    /**
     * 描述
     */
    private String description;

}