package com.ananops.provider.model.vo;

import com.ananops.base.dto.BaseVo;
import lombok.Data;
    import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 加盟服务商查询返回的工单信息Vo
 *
 * Created by bingyueduan on 2020/1/3.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WorkOrderVo extends BaseVo {

    /**
     * 工单类型; 巡检(inspection)和维修维护(maintain)
     */
    private String type;

    /**
     * 工单状态; 待处理/已分配/进行中
     */
    private Integer status;

    /**
     * 项目ID
     */
    private Long projectId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 客户负责人ID
     */
    private Long principalId;

    /**
     * 客户负责人名称
     */
    private String principalName;

    /**
     * 服务商ID
     */
    private Long facilitatorId;

    /**
     * 服务商名称
     */
    private String facilitatorName;

    /**
     * 工程师ID
     */
    private Long maintainerId;

    /**
     * 工程师名称
     */
    private String maintainerName;

    /**
     * 工单名称
     */
    private String taskName;

    /**
     * 预计开始时间
     */
    private Date scheduledStartTime;

    /**
     * 工单内容描述
     */
    private String description;
}
