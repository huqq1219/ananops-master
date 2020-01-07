package com.ananops.provider.model.dto;

import com.ananops.core.mybatis.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 工单Dto
 *
 * Created by bingyueduan on 2020/1/3.
 */
@Data
@ApiModel(value = "工单查询Dto")
public class WorkOrderDto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -2385223706466210182L;

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
     * 客户负责人ID
     */
    private Long principalId;

    /**
     * 服务商ID
     */
    private Long facilitatorId;

    /**
     * 工程师ID
     */
    private Long maintainerId;

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
