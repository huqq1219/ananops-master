package com.ananops.provider.service.impl;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.provider.mapper.SpcCompanyEngineerMapper;
import com.ananops.provider.mapper.SpcCompanyMapper;
import com.ananops.provider.mapper.SpcEngineerMapper;
import com.ananops.provider.model.domain.MdmcTask;
import com.ananops.provider.model.domain.SpcCompany;
import com.ananops.provider.model.dto.*;
import com.ananops.provider.model.dto.group.GroupSaveDto;
import com.ananops.provider.model.service.UacGroupFeignApi;
import com.ananops.provider.model.vo.CompanyVo;
import com.ananops.provider.model.vo.WorkOrderDetailVo;
import com.ananops.provider.model.vo.WorkOrderVo;
import com.ananops.provider.service.ImcTaskFeignApi;
import com.ananops.provider.service.MdmcTaskFeignApi;
import com.ananops.provider.service.PmcProjectFeignApi;
import com.ananops.provider.service.SpcWorkOrderService;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 操作加盟服务商WorkOrder的Service接口实现类
 *
 * Created by bingyueduan on 2020/1/3.
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SpcWorkOrderServiceImpl implements SpcWorkOrderService {

    @Resource
    private SpcEngineerMapper spcEngineerMapper;

    @Resource
    private SpcCompanyMapper spcCompanyMapper;

    @Resource
    private SpcCompanyEngineerMapper spcCompanyEngineerMapper;

    @Resource
    private ImcTaskFeignApi imcTaskFeignApi;

    @Resource
    private MdmcTaskFeignApi mdmcTaskFeignApi;

    @Resource
    private PmcProjectFeignApi pmcProjectFeignApi;

    @Resource
    private UacGroupFeignApi uacGroupFeignApi;

    @Override
    public List<WorkOrderVo> queryAllWorkOrders(WorkOrderDto workOrderDto, LoginAuthDto loginAuthDto) {
        List<WorkOrderVo> workOrderVos = new ArrayList<>();
        Long groupId = loginAuthDto.getGroupId();
        String workOrderType = workOrderDto.getType();
        Integer workOrderStatus = workOrderDto.getStatus();
//        // 根据登录用户的UserId查询工程师信息
//        SpcEngineer query = new SpcEngineer();
//        query.setUserId(userId);
//        SpcEngineer result = spcEngineerMapper.selectOne(query);
//        // 查询该业务操作员属于哪个服务商
//        Long engineerId = result.getId();
//        SpcCompanyEngineer queryEn = new SpcCompanyEngineer();
//        queryEn.setEngineerId(engineerId);
//        Long companyId = spcCompanyEngineerMapper.selectOne(queryEn).getCompanyId();

        TaskQueryDto taskQueryDto = new TaskQueryDto();
        taskQueryDto.setUserId(groupId);
        List<TaskDto> imcTaskDtos = imcTaskFeignApi.getByFacilitatorId(taskQueryDto).getResult();
        MdmcQueryDto mdmcQueryDto = new MdmcQueryDto();
        mdmcQueryDto.setId(groupId);
        mdmcQueryDto.setRoleCode("fac_service");
        List<MdmcTask> mdmcTaskDtos = mdmcTaskFeignApi.getTaskListByIdAndStatus(mdmcQueryDto).getResult();

        // 按工单状态筛选
        if (!StringUtils.isEmpty(workOrderStatus)) {
            List<MdmcTask> newMdmcTaskDtos = new ArrayList<>();
            for (MdmcTask mdmcTaskDto : mdmcTaskDtos) {
                if (workOrderStatus.equals(mdmcTaskDto.getStatus())) {
                    newMdmcTaskDtos.add(mdmcTaskDto);
                }
            }
            mdmcTaskDtos = newMdmcTaskDtos;
            List<TaskDto> newImcTaskDtos = new ArrayList<>();
            for (TaskDto imcTaskDto : imcTaskDtos) {
                if (workOrderStatus.equals(imcTaskDto.getStatus())) {
                    newImcTaskDtos.add(imcTaskDto);
                }
            }
            imcTaskDtos = newImcTaskDtos;
        }

        // 按工单类型筛选
        if (!StringUtils.isEmpty(workOrderType) && "maintain".equals(workOrderType)) {
            for (MdmcTask mdmcTaskDto : mdmcTaskDtos) {
                WorkOrderVo workOrderVo = new WorkOrderVo();
                workOrderDto.setType("maintain");
                try {
                    BeanUtils.copyProperties(workOrderVo, mdmcTaskDto);
                } catch (Exception e) {
                    log.error("维修维护工单Dto与工单Dto属性拷贝异常");
                    e.printStackTrace();
                }
                workOrderVos.add(workOrderVo);
            }
        } else if (!StringUtils.isEmpty(workOrderType) && "inspection".equals(workOrderType)) {
            for (TaskDto imcTaskDto : imcTaskDtos) {
                WorkOrderVo workOrderVo = new WorkOrderVo();
                workOrderDto.setType("inspection");
                try {
                    BeanUtils.copyProperties(workOrderVo, imcTaskDto);
                } catch (Exception e) {
                    log.error("巡检工单Dto与工单Dto属性拷贝异常");
                    e.printStackTrace();
                }
                workOrderVos.add(workOrderVo);
            }
        } else {
            for (MdmcTask mdmcTaskDto : mdmcTaskDtos) {
                WorkOrderVo workOrderVo = new WorkOrderVo();
                workOrderDto.setType("maintain");
                try {
                    BeanUtils.copyProperties(workOrderVo, mdmcTaskDto);
                } catch (Exception e) {
                    log.error("维修维护工单Dto与工单Dto属性拷贝异常");
                    e.printStackTrace();
                }
                workOrderVos.add(workOrderVo);
            }
            for (TaskDto imcTaskDto : imcTaskDtos) {
                WorkOrderVo workOrderVo = new WorkOrderVo();
                workOrderDto.setType("inspection");
                try {
                    BeanUtils.copyProperties(workOrderVo, imcTaskDto);
                } catch (Exception e) {
                    log.error("巡检工单Dto与工单Dto属性拷贝异常");
                    e.printStackTrace();
                }
                workOrderVos.add(workOrderVo);
            }
        }

        return workOrderVos;
    }

    @Override
    public WorkOrderDetailVo queryByWorkOrderId(WorkOrderQueryDto workOrderQueryDto) {
        WorkOrderDetailVo workOrderDetailVo = new WorkOrderDetailVo();
        Long taskId = workOrderQueryDto.getId();
        Long projectId = null;
        Long groupId = null;
        String workOrderType = workOrderQueryDto.getType();
        // 填充工单信息
        if (!Strings.isNullOrEmpty(workOrderType) && "inspection".equals(workOrderType)) {
            log.info("查询巡检工单：taskId=" + taskId);
            TaskDto taskDto = imcTaskFeignApi.getTaskByTaskId(taskId).getResult();
            workOrderDetailVo.setType("inspection");
            workOrderDetailVo.setInspectionTask(taskDto);
            projectId = taskDto.getProjectId();
            groupId = taskDto.getFacilitatorId();
        } else if (!Strings.isNullOrEmpty(workOrderType) && "maintain".equals(workOrderType)) {
            log.info("查询维修维护工单：taskId=" + taskId);
            MdmcTask mdmcTaskDto = mdmcTaskFeignApi.getTaskByTaskId(taskId).getResult();
            workOrderDetailVo.setType("maintain");
            workOrderDetailVo.setMaintainTask(mdmcTaskDto);
            projectId = mdmcTaskDto.getProjectId();
            groupId = mdmcTaskDto.getFacilitatorId();
        }
        // 填充项目信息
        log.info("工单项目ID：projectId=" + projectId);
        PmcProjectDto pmcProjectDto = pmcProjectFeignApi.getProjectByProjectId(projectId).getResult();
        workOrderDetailVo.setPmcProjectDto(pmcProjectDto);
        // 填充服务商信息
        CompanyVo companyVo = new CompanyVo();
        SpcCompany queryC = new SpcCompany();
        queryC.setGroupId(groupId);
        SpcCompany spcCompany = spcCompanyMapper.selectOne(queryC);
        if (!StringUtils.isEmpty(groupId)) {
            GroupSaveDto groupSaveDto = uacGroupFeignApi.getUacGroupById(groupId).getResult();
            try {
                BeanUtils.copyProperties(companyVo, spcCompany);
                BeanUtils.copyProperties(companyVo, groupSaveDto);
            } catch (Exception e) {
                log.error("queryByCompanyId 服务商Dto与用户组Dto属性拷贝异常");
                e.printStackTrace();
            }
        }
        workOrderDetailVo.setCompanyVo(companyVo);
        return workOrderDetailVo;
    }
}
