package com.ananops.provider.model.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Table(name = "an_rdc_approve")
public class Approve {
    /**
     * 审批编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("approveId")
    private Long id;
    
    /**
     * 版本号
     */
    private Integer version;

    /**
     * 上级审批人编号
     */
    @Column(name = "previous_approver_id")
    private Long previousApproverId;

    /**
     * 上级审批人
     */
    @Column(name = "previous_approver")
    private String previousApprover;

    /**
     * 当前审批人编号
     */
    @Column(name = "current_approver_id")
    private Long currentApproverId;

    /**
     * 当前审批人
     */
    @Column(name = "current_approver")
    private String currentApprover;
    
    /**
     * 下一审批人编号
     */
    @Column(name = "next_approver_id")
    private Long nextApproverId;
    
    /**
     * 下一审批人
     */
    @Column(name = "next_approver")
    private String nextApprover;

    /**
     * 审批对象类型
     */
    @Column(name = "object_type")
    private Integer objectType;

    /**
     * 审批对象
     */
    @Column(name = "object_id")
    private Long objectId;

    /**
     * 申请人编号
     */
    @Column(name = "applicant_id")
    private Long applicantId;

    /**
     * 申请人
     */
    private String applicant;

    /**
     * 审批结果
     */
    private String result;

    /**
     * 审批意见
     */
    private String suggestion;

    
}