package com.ananops.provider.model.vo;

import com.ananops.base.dto.BaseVo;
import com.ananops.provider.model.domain.MdmcTask;
import com.ananops.provider.model.dto.PmcProjectDto;
import com.ananops.provider.model.dto.TaskDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 加盟服务商查询返回的工单详细信息Vo
 *
 * Created by bingyueduan on 2020/1/3.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WorkOrderDetailVo extends BaseVo {

    /**
     * 工单类型; 巡检(inspection)和维修维护(maintain)
     */
    private String type;

    /**
     * 维修维护工单详情
     */
    private MdmcTask maintainTask;

    /**
     * 巡检工单详情
     */
    private TaskDto inspectionTask;

    /**
     * 工单关联的项目详情
     */
    private PmcProjectDto pmcProjectDto;

    /**
     * 工单相关服务商信息
     */
    private CompanyVo companyVo;

    /**
     * 工单相关工程师信息
     */
    private List<EngineerVo> engineerVos;
}
