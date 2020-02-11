package com.ananops.provider.service.impl;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.base.exception.BusinessException;
import com.ananops.provider.mapper.SpcCompanyEngineerMapper;
import com.ananops.provider.mapper.SpcCompanyMapper;
import com.ananops.provider.mapper.SpcEngineerMapper;
import com.ananops.provider.model.domain.MdmcTask;
import com.ananops.provider.model.domain.SpcCompany;
import com.ananops.provider.model.dto.*;
import com.ananops.provider.model.dto.group.GroupSaveDto;
import com.ananops.provider.model.dto.user.UserInfoDto;
import com.ananops.provider.model.service.UacGroupFeignApi;
import com.ananops.provider.model.service.UacUserFeignApi;
import com.ananops.provider.model.vo.CompanyVo;
import com.ananops.provider.model.vo.EngineerVo;
import com.ananops.provider.model.vo.WorkOrderDetailVo;
import com.ananops.provider.model.vo.WorkOrderVo;
import com.ananops.provider.service.*;
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
    private ImcItemFeignApi imcItemFeignApi;

    @Resource
    private MdmcTaskFeignApi mdmcTaskFeignApi;

    @Resource
    private PmcProjectFeignApi pmcProjectFeignApi;

    @Resource
    private UacGroupFeignApi uacGroupFeignApi;

    @Resource
    private SpcEngineerService spcEngineerService;

    private UacUserFeignApi uacUserFeignApi;

    @Override
    public List<WorkOrderVo> queryAllWorkOrders(WorkOrderStatusQueryDto workOrderStatusQueryDto, LoginAuthDto loginAuthDto) {
        List<WorkOrderVo> workOrderVos = new ArrayList<>();
        Long groupId = loginAuthDto.getGroupId();
        String workOrderType = workOrderStatusQueryDto.getType();
        Integer workOrderStatus = workOrderStatusQueryDto.getStatus();

        TaskQueryDto taskQueryDto = new TaskQueryDto();
        taskQueryDto.setUserId(groupId);
        log.info("登录用户的GroupId=" + groupId);
        List<TaskDto> imcTaskDtos = imcTaskFeignApi.getByFacilitatorId(taskQueryDto).getResult();
        log.info("巡检任务查询结果：imcTaskDtos=" + imcTaskDtos);
        MdmcQueryDto mdmcQueryDto = new MdmcQueryDto();
        mdmcQueryDto.setId(groupId);
        mdmcQueryDto.setRoleCode("fac_service");
        List<MdmcTask> mdmcTaskDtos = mdmcTaskFeignApi.getTaskListByIdAndStatus(mdmcQueryDto).getResult();
        log.info("巡检任务查询结果：mdmcTaskDtos=" + mdmcTaskDtos);

        // 按工单状态筛选
        if (!StringUtils.isEmpty(workOrderStatus)) {
            if (mdmcTaskDtos != null) {
                List<MdmcTask> newMdmcTaskDtos = new ArrayList<>();
                for (MdmcTask mdmcTaskDto : mdmcTaskDtos) {
                    if (workOrderStatus.equals(mdmcTaskDto.getStatus())) {
                        newMdmcTaskDtos.add(mdmcTaskDto);
                    }
                }
                mdmcTaskDtos = newMdmcTaskDtos;
            }
            if (imcTaskDtos != null) {
                List<TaskDto> newImcTaskDtos = new ArrayList<>();
                for (TaskDto imcTaskDto : imcTaskDtos) {
                    if (workOrderStatus.equals(imcTaskDto.getStatus())) {
                        newImcTaskDtos.add(imcTaskDto);
                    }
                }
                imcTaskDtos = newImcTaskDtos;
            }

        }

        // 按工单类型筛选
        if (!StringUtils.isEmpty(workOrderType) && "maintain".equals(workOrderType)) {
            if (mdmcTaskDtos != null) {
                for (MdmcTask mdmcTaskDto : mdmcTaskDtos) {
                    WorkOrderVo workOrderVo = new WorkOrderVo();
                    workOrderVo.setType("maintain");
                    try {
                        BeanUtils.copyProperties(workOrderVo, mdmcTaskDto);
                    } catch (Exception e) {
                        log.error("维修维护工单Dto与工单Dto属性拷贝异常");
                        e.printStackTrace();
                    }
                    workOrderVos.add(workOrderVo);
                }
            }
        } else if (!StringUtils.isEmpty(workOrderType) && "inspection".equals(workOrderType)) {
            if (imcTaskDtos != null) {
                for (TaskDto imcTaskDto : imcTaskDtos) {
                    WorkOrderVo workOrderVo = new WorkOrderVo();
                    workOrderVo.setType("inspection");
                    try {
                        BeanUtils.copyProperties(workOrderVo, imcTaskDto);
                    } catch (Exception e) {
                        log.error("巡检工单Dto与工单Dto属性拷贝异常");
                        e.printStackTrace();
                    }
                    workOrderVos.add(workOrderVo);
                }
            }
        } else {
            if (mdmcTaskDtos != null) {
                for (MdmcTask mdmcTaskDto : mdmcTaskDtos) {
                    WorkOrderVo workOrderVo = new WorkOrderVo();
                    workOrderVo.setType("maintain");
                    try {
                        BeanUtils.copyProperties(workOrderVo, mdmcTaskDto);
                    } catch (Exception e) {
                        log.error("维修维护工单Dto与工单Dto属性拷贝异常");
                        e.printStackTrace();
                    }
                    workOrderVos.add(workOrderVo);
                }
            }
            if (imcTaskDtos != null) {
                for (TaskDto imcTaskDto : imcTaskDtos) {
                    WorkOrderVo workOrderVo = new WorkOrderVo();
                    workOrderVo.setType("inspection");
                    try {
                        BeanUtils.copyProperties(workOrderVo, imcTaskDto);
                    } catch (Exception e) {
                        log.error("巡检工单Dto与工单Dto属性拷贝异常");
                        e.printStackTrace();
                    }
                    workOrderVos.add(workOrderVo);
                }
            }
        }

        // 填充名字信息
        decorateWorkOrder(workOrderVos);

        return workOrderVos;
    }

    /**
     * 填充名字信息
     *
     * @param workOrderVos
     */
    private void decorateWorkOrder(List<WorkOrderVo> workOrderVos) {

        if (workOrderVos != null) {
            for (WorkOrderVo workOrderVo : workOrderVos) {
                // 填充项目名称,客户负责人名称名称
                Long projectId = workOrderVo.getProjectId();
                if (projectId != null) {
                    PmcProjectDto pmcProjectDto = pmcProjectFeignApi.getProjectByProjectId(projectId).getResult();
                    String projectName = pmcProjectDto.getProjectName();
                    String aOneName = pmcProjectDto.getAOneName();
                    String aTwoName = pmcProjectDto.getATwoName();
                    String aThreeName = pmcProjectDto.getAThreeName();
                    workOrderVo.setProjectName(projectName);
                    if (!StringUtils.isEmpty(aOneName)) {
                        workOrderVo.setFacilitatorName(aOneName);
                    } else if (!StringUtils.isEmpty(aTwoName)) {
                        workOrderVo.setFacilitatorName(aTwoName);
                    } else if (!StringUtils.isEmpty(aThreeName)) {
                        workOrderVo.setFacilitatorName(aThreeName);
                    }
                }
                // 填充服务商名称
                Long groupId = workOrderVo.getFacilitatorId();
                if (groupId != null) {
                    GroupSaveDto groupSaveDto = uacGroupFeignApi.getUacGroupById(groupId).getResult();
                    workOrderVo.setFacilitatorName(groupSaveDto.getGroupName());
                }
                // 填充工程师名称
                Long userId = workOrderVo.getMaintainerId();
                if (userId != null) {
                    UserInfoDto userInfoDto = uacUserFeignApi.getUacUserById(userId).getResult();
                    workOrderVo.setMaintainerName(userInfoDto.getUserName());
                }
            }
        }
    }

    /**
     * 根据工单Id查询工单信息
     *
     * @param workOrderQueryDto
     *
     * @return
     */
    @Override
    public WorkOrderDetailVo queryByWorkOrderId(WorkOrderQueryDto workOrderQueryDto) {
        WorkOrderDetailVo workOrderDetailVo = new WorkOrderDetailVo();
        Long taskId = workOrderQueryDto.getId();//获取工单Id
        Long projectId = null;
        Long groupId = null;
        String workOrderType = workOrderQueryDto.getType();//获取工单类型
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
        if(projectId != null){
            PmcProjectDto pmcProjectDto = pmcProjectFeignApi.getProjectByProjectId(projectId).getResult();
            workOrderDetailVo.setPmcProjectDto(pmcProjectDto);
        }
        // 填充服务商信息
        CompanyVo companyVo = new CompanyVo();
        SpcCompany queryC = new SpcCompany();
        queryC.setGroupId(groupId);
        SpcCompany spcCompany = spcCompanyMapper.selectOne(queryC);
        if (!StringUtils.isEmpty(groupId)) {//如果组织Id非空
            GroupSaveDto groupSaveDto = uacGroupFeignApi.getUacGroupById(groupId).getResult();
            try {
                if (spcCompany != null)
                    BeanUtils.copyProperties(companyVo, spcCompany);

                if (groupSaveDto != null)
                    BeanUtils.copyProperties(companyVo, groupSaveDto);
            } catch (Exception e) {
                log.error("queryByCompanyId 服务商Dto与用户组Dto属性拷贝异常");
                e.printStackTrace();
            }
        }
        //填充相关工程师信息
        log.info("工单项目ID：projectId=" + projectId);
        List<Long> engineerIdList = new ArrayList<>();
        List<EngineerVo> engineerVos = new ArrayList<>();
        if(!Strings.isNullOrEmpty(workOrderType) && "maintain".equals(workOrderType)){
            //如果当前是巡检任务
            engineerIdList.add(mdmcTaskFeignApi.getTaskByTaskId(taskId).getResult().getMaintainerId());
        }else if(!Strings.isNullOrEmpty(workOrderType) && "inspection".equals(workOrderType)){
            //如果当前是维修维护任务
            List<ItemDto> itemDtoList = imcTaskFeignApi.getTaskByTaskId(taskId).getResult().getItemDtoList();
            itemDtoList.forEach(itemDto -> {
                engineerIdList.add(itemDto.getMaintainerId());
            });
        }
        engineerIdList.forEach(engineerId->{//根据工单对应的全部工程师Id获取全部的工程师
            if(engineerId != null){
                EngineerVo engineerVo = spcEngineerService.queryByEngineerId(engineerId);
                engineerVos.add(engineerVo);
            }
        });
        workOrderDetailVo.setEngineerVos(engineerVos);
        workOrderDetailVo.setCompanyVo(companyVo);
        return workOrderDetailVo;
    }

    /**
     * 获取工单对应的全部工程师列表
     * @param workOrderDto
     * @return
     */
    @Override
    public List<EngineerDto> engineersDtoList(WorkOrderDto workOrderDto) {
        WorkOrderDetailVo workOrderDetailVo = new WorkOrderDetailVo();
        Long taskId = workOrderDto.getId();
        Long projectId = null;
        String workOrderType = workOrderDto.getType();
        //  通过工单ID查询对应项目ID
        if (!Strings.isNullOrEmpty(workOrderType) && "inspection".equals(workOrderType)) {
            log.info("查询巡检工单：taskId=" + taskId);
            TaskDto taskDto = imcTaskFeignApi.getTaskByTaskId(taskId).getResult();
            workOrderDetailVo.setType("inspection");
            workOrderDetailVo.setInspectionTask(taskDto);
            projectId = taskDto.getProjectId();
        } else if (!Strings.isNullOrEmpty(workOrderType) && "maintain".equals(workOrderType)) {
            log.info("查询维修维护工单：taskId=" + taskId);
            MdmcTask mdmcTaskDto = mdmcTaskFeignApi.getTaskByTaskId(taskId).getResult();
            workOrderDetailVo.setType("maintain");
            workOrderDetailVo.setMaintainTask(mdmcTaskDto);
            projectId = mdmcTaskDto.getProjectId();
        }
        // 通过项目ID查询对应项目下的工程师信息
        log.info("工单项目ID：projectId=" + projectId);
        List<EngineerDto> result = new ArrayList<>();
        result = spcEngineerService.getEngineersByProjectId(projectId);
        return result;
    }

    /**
     * 为维修维护类型的工单分配工程师
     * @param engineerDistributeDto
     */
    @Transactional
    @Override
    public void distributeEngineerForMdmc(EngineerDistributeDto engineerDistributeDto,LoginAuthDto loginAuthDto){
        Long taskId = engineerDistributeDto.getTaskId();
        Long maintainerId =engineerDistributeDto.getEngineerId();
        //为对应的维修维护任务添加工程师
        MdmcChangeMaintainerDto mdmcChangeMaintainerDto = new MdmcChangeMaintainerDto();
        mdmcChangeMaintainerDto.setLoginAuthDto(loginAuthDto);
        mdmcChangeMaintainerDto.setMaintainerId(maintainerId);
        mdmcChangeMaintainerDto.setTaskId(taskId);
        mdmcTaskFeignApi.modifyMaintainerByTaskId(mdmcChangeMaintainerDto);
        //为对应的维修维护任务修改状态
        MdmcChangeStatusDto mdmcChangeStatusDto = new MdmcChangeStatusDto();
        mdmcChangeStatusDto.setStatus(5);//将维修维护任务的状态修改为“已分配维修工，待维修工接单”
        mdmcChangeStatusDto.setLoginAuthDto(loginAuthDto);
        mdmcChangeStatusDto.setTaskId(taskId);
        mdmcTaskFeignApi.modifyTaskStatusByTaskId(mdmcChangeStatusDto);

    }

    /**
     * 为巡检类型的任务分配工程师
     * @param engineerDistributeDto
     */
    @Transactional
    @Override
    public void distributeEngineerForImc(EngineerDistributeDto engineerDistributeDto,LoginAuthDto loginAuthDto){
        Long itemId = engineerDistributeDto.getTaskId();//获取待分配巡检任务子项的Id
        Long maintainerId = engineerDistributeDto.getEngineerId();//获取待分配的工程师Id
        //为对应的巡检任务子项添加工程师
        ItemChangeMaintainerDto itemChangeMaintainerDto = new ItemChangeMaintainerDto();
        itemChangeMaintainerDto.setItemId(itemId);
        itemChangeMaintainerDto.setMaintainerId(maintainerId);
        imcItemFeignApi.modifyMaintainerByItemId(itemChangeMaintainerDto);
        //为对应的巡检任务子项修改状态
        ImcItemChangeStatusDto imcItemChangeStatusDto = new ImcItemChangeStatusDto();
        imcItemChangeStatusDto.setItemId(itemId);
        imcItemChangeStatusDto.setStatus(2);//将巡检任务子项的状态修改为“等待工程师接单”
        imcItemChangeStatusDto.setLoginAuthDto(loginAuthDto);
        imcItemFeignApi.modifyImcItemStatus(imcItemChangeStatusDto);
    }

//    /**
//     * 服务商转单操作（拒单）
//     * @param workOrderDto
//     */
//    @Override
//    public void transferWorkOrder(WorkOrderDto workOrderDto,LoginAuthDto loginAuthDto) {
//        Long taskId = workOrderDto.getId();
//        String workOrderType = workOrderDto.getType();
//        if (!Strings.isNullOrEmpty(workOrderType) && "inspection".equals(workOrderType)) {
//            log.info("查询巡检工单：taskId=" + taskId);
//            ConfirmImcTaskDto confirmImcTaskDto = new ConfirmImcTaskDto();
//            confirmImcTaskDto.setLoginAuthDto(loginAuthDto);
//            confirmImcTaskDto.setTaskId(taskId);
//            imcTaskFeignApi.refuseImcTaskByFacilitator(confirmImcTaskDto);//服务商拒单
//
//        } else if (!Strings.isNullOrEmpty(workOrderType) && "maintain".equals(workOrderType)) {
//            log.info("查询维修维护工单：taskId=" + taskId);
//            RefuseMdmcTaskDto refuseMdmcTaskDto = new RefuseMdmcTaskDto();
//            refuseMdmcTaskDto.setLoginAuthDto(loginAuthDto);
//            refuseMdmcTaskDto.setTaskId(taskId);
//            mdmcTaskFeignApi.refuseMdmcTaskByFacilitator(refuseMdmcTaskDto);
//        }
//    }

    /**
     * 查询所有待审批的工单
     * @param workOrderStatusQueryDto
     * @param loginAuthDto
     * @return
     */
    @Override
    public List<WorkOrderVo> queryAllUnConfirmedWorkOrders(WorkOrderStatusQueryDto workOrderStatusQueryDto, LoginAuthDto loginAuthDto){
        List<WorkOrderVo> workOrderVoList = this.queryAllWorkOrders(workOrderStatusQueryDto, loginAuthDto);
        List<WorkOrderVo> unconfirmedWorkOrders = new ArrayList<>();
        for(WorkOrderVo workOrderVo:workOrderVoList){
            Long taskId = workOrderVo.getId();//工单Id（维修维护or巡检的）
            String type = workOrderVo.getType();//工单类型，巡检(inspection)和维修维护(maintain)
            if("inspection".equals(type)){//如果当前工单类型是巡检工单
                int status = imcTaskFeignApi.getTaskByTaskId(taskId).getResult().getStatus();
                if(status == 2){
                    //如果巡检任务不是处于“等待服务商接单”的阶段
                    unconfirmedWorkOrders.add(workOrderVo);//将次任务从列表中移除掉
                }
            }else if("maintain".equals(type)){//如果当前工单类型是维修维护工单
                int status = mdmcTaskFeignApi.getTaskByTaskId(taskId).getResult().getStatus();
                if(status == 3 || status == 7){
                    //如果维修维护任务不是处于“服务商待接单”或者是“服务商待审核备品备件工单”这一状态
                    unconfirmedWorkOrders.add(workOrderVo);//将此次任务从列表中移除
                }
            }
        }
//        workOrderVoVoList.forEach(workOrderVo -> {
//            Long taskId = workOrderVo.getId();//工单Id（维修维护or巡检的）
//            String type = workOrderVo.getType();//工单类型，巡检(inspection)和维修维护(maintain)
//            if("inspection".equals(type)){//如果当前工单类型是巡检工单
//                int status = imcTaskFeignApi.getTaskByTaskId(taskId).getResult().getStatus();
//                if(status != 2){
//                    //如果巡检任务不是处于“等待服务商接单”的阶段
//                    workOrderVoVoList.remove(workOrderVo);//将次任务从列表中移除掉
//                }
//            }else if("maintain".equals(type)){//如果当前工单类型是维修维护工单
//                int status = mdmcTaskFeignApi.getTaskByTaskId(taskId).getResult().getStatus();
//                if(status != 3 && status != 7){
//                    //如果维修维护任务不是处于“服务商待接单”或者是“服务商待审核备品备件工单”这一状态
//                    workOrderVoVoList.remove(workOrderVo);//将此次任务从列表中移除
//                }
//            }
//        });
        return unconfirmedWorkOrders;
    }

    /**
     * 获取所有未分配工程师的工单
     * @param workOrderStatusQueryDto
     * @param loginAuthDto
     * @return
     */
    @Override
    public List<WorkOrderVo> queryAllUnDistributedWorkOrders(WorkOrderStatusQueryDto workOrderStatusQueryDto, LoginAuthDto loginAuthDto){
        List<WorkOrderVo> workOrderVoVoList = this.queryAllWorkOrders(workOrderStatusQueryDto, loginAuthDto);
        List<WorkOrderVo> undistributedWorkOrders = new ArrayList<>();
        workOrderVoVoList.forEach(workOrderVo -> {
            Long taskId = workOrderVo.getId();//工单Id（维修维护or巡检的）
            String type = workOrderVo.getType();//工单类型，巡检(inspection)和维修维护(maintain)
            if("inspection".equals(type)){//如果当前工单类型是巡检工单
                int inspectionTaskStatus = imcTaskFeignApi.getTaskByTaskId(taskId).getResult().getStatus();
                List<ItemDto> itemDtoList = imcTaskFeignApi.getTaskByTaskId(taskId).getResult().getItemDtoList();
                if(inspectionTaskStatus == 3){
                    int mark=0;
                    for(ItemDto itemDto : itemDtoList){
                        if(itemDto.getStatus()==1){
                            mark=1;
                            break;
                        }
                    }
                    if(mark==1){
                        //如果巡检任务的全部任务子项仍然有没分配工程师的
                        undistributedWorkOrders.add(workOrderVo);//将此任务从列表中移除掉
                    }
                }
            }else if("maintain".equals(type)){//如果当前工单类型是维修维护工单
                int status = mdmcTaskFeignApi.getTaskByTaskId(taskId).getResult().getStatus();
                if(status == 4){
                    //如果维修维护任务处于“服务商已接单，待分配维修工”这一状态
                    undistributedWorkOrders.add(workOrderVo);//将此次任务从列表中移除
                }
            }
        });
        return undistributedWorkOrders;
    }

    @Override
    public WorkOrderDetailVo confirmWorkOrder(WorkOrderConfirmDto workOrderConfirmDto,LoginAuthDto loginAuthDto){
        WorkOrderQueryDto workOrderQueryDto = workOrderConfirmDto.getWorkOrderQueryDto();
        int decision = workOrderConfirmDto.getDecision();
        Long workOrderId = workOrderQueryDto.getId();
        String workOrderType =  workOrderQueryDto.getType();
        //获取工单的细节
        WorkOrderDetailVo workOrderDetailVo = this.queryByWorkOrderId(workOrderQueryDto);
        if(!Strings.isNullOrEmpty(workOrderType) && "maintain".equals(workOrderType)){
            //如果当前审批的是维修维护工单
            MdmcTask mdmcTask = workOrderDetailVo.getMaintainTask();
            int maintainStatus = mdmcTask.getStatus();
            MdmcChangeStatusDto mdmcChangeStatusDto = new MdmcChangeStatusDto();
            mdmcChangeStatusDto.setTaskId(workOrderId);
            mdmcChangeStatusDto.setLoginAuthDto(loginAuthDto);
            switch (maintainStatus){
                case 3://服务商待接单
                    if(decision == 0){
                        //如果服务商拒单
                        mdmcChangeStatusDto.setStatus(14);
                        mdmcTaskFeignApi.modifyTaskStatusByTaskId(mdmcChangeStatusDto);
                    }else if(decision == 1){
                        //如果服务商接单
                        mdmcChangeStatusDto.setStatus(4);
                        mdmcTaskFeignApi.modifyTaskStatusByTaskId(mdmcChangeStatusDto);
                    }else{
                        throw new BusinessException(ErrorCodeEnum.SPC100850019);//无此审批操作
                    }
                case 7://服务商待审核备品备件工单
                    if(decision == 0){
                        //如果服务商拒绝备品备件申请
                        mdmcChangeStatusDto.setStatus(16);
                        mdmcTaskFeignApi.modifyTaskStatusByTaskId(mdmcChangeStatusDto);
                    }else if(decision == 1){
                        //如果服务商同意备品备件申请
                        mdmcChangeStatusDto.setStatus(8);
                        mdmcTaskFeignApi.modifyTaskStatusByTaskId(mdmcChangeStatusDto);
                    }else{
                        throw new BusinessException(ErrorCodeEnum.SPC100850019);//无此审批操作
                    }
                default:
                    throw new BusinessException(ErrorCodeEnum.GL9999084); //当前状态不允许服务商审批
            }
        }else if(!Strings.isNullOrEmpty(workOrderType) && "inspection".equals(workOrderType)){
            //如果当前审批的是巡检工单
            TaskDto imcTaskDto = workOrderDetailVo.getInspectionTask();
            int inspectionStatus = imcTaskDto.getStatus();
            if (inspectionStatus == 2) {//如果工单状态是“已分配服务商，等待服务商接单”
                ConfirmImcTaskDto confirmImcTaskDto = new ConfirmImcTaskDto();
                confirmImcTaskDto.setLoginAuthDto(loginAuthDto);
                confirmImcTaskDto.setTaskId(workOrderId);
                if (decision == 0) {
                    //否决
                    imcTaskFeignApi.refuseImcTaskByFacilitator(confirmImcTaskDto);//服务商拒单
                } else if (decision == 1) {
                    //同意
                    imcTaskFeignApi.acceptImcTaskByFacilitator(confirmImcTaskDto);//服务商接单
                } else {
                    throw new BusinessException(ErrorCodeEnum.SPC100850019);//无此审批操作
                }
            }else{
                throw new BusinessException(ErrorCodeEnum.GL9999084); //当前状态不允许服务商审批
            }

        }
        return null;
    }

}
