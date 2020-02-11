package com.ananops.provider.service;


import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.IService;
import com.ananops.provider.model.domain.ImcInspectionTask;
import com.ananops.provider.model.dto.*;

import java.util.List;

/**
 * Created by rongshuai on 2019/11/27 19:29
 */
public interface ImcInspectionTaskService extends IService<ImcInspectionTask> {
    ImcAddInspectionTaskDto saveTask(ImcAddInspectionTaskDto imcAddInspectionTaskDto, LoginAuthDto loginAuthDto);//创建一条巡检任务

    ImcInspectionTask getTaskByTaskId(Long taskId);//根据巡检任务的ID，获得当前巡检任务的详情

    void deleteTaskById(Long taskId);

    ImcInspectionTask modifyTaskStatus(ImcTaskChangeStatusDto imcTaskChangeStatusDto, LoginAuthDto loginAuthDto);//修改巡检任务的状态

    List<ImcInspectionTask> getTaskByStatus(TaskQueryDto taskQueryDto);//根据巡检任务的状态查询对应的任务

    ImcInspectionTask modifyTaskName(TaskNameChangeDto taskNameChangeDto, LoginAuthDto loginAuthDto);

    TaskChangeFacilitatorDto modifyFacilitator(TaskChangeFacilitatorDto taskChangeFacilitatorDto);

    List<ImcInspectionTask> getTaskByProjectId(TaskQueryDto taskQueryDto);//根据项目Id获取所有对应的巡检任务

    List<ImcInspectionTask> getTaskByUserId(TaskQueryDto taskQueryDto);//根据用户（包括甲方和服务商）id查询对应的巡检任务

    List<ImcInspectionTask> getTaskByUserIdAndStatus(TaskQueryDto taskQueryDto);//根据用户（包括甲方和服务商）id查询指定状态的巡检任务

    Boolean isTaskFinish(Long taskId);//判断巡检任务中的全部任务子项是否执行完成

    List<ImcInspectionTask> getTaskByFacilitatorId(TaskQueryDto taskQueryDto);//根据服务商ID获取对应的巡检任务

    List<ImcInspectionTask> getTaskByFacilitatorIdAndStatus(TaskQueryDto taskQueryDto);//根据服务商ID查询指定状态的巡检任务

    ImcTaskChangeStatusDto refuseImcTaskByFacilitator(ConfirmImcTaskDto confirmImcTaskDto);//服务商拒单

    List<ImcInspectionTask> getAllUnauthorizedTaskByPrincipalId(TaskQueryDto taskQueryDto);//查询当前甲方负责人下面的全部未授权的任务

    List<ImcInspectionTask> getAllDeniedTaskByPrincipalId(TaskQueryDto taskQueryDto);//查询当前甲方负责人下面的全部未授权的任务

    ImcTaskChangeStatusDto acceptImcTaskByPrincipal(ImcTaskChangeStatusDto imcTaskChangeStatusDto);//甲方负责人同意一项巡检任务

    ImcTaskChangeStatusDto acceptImcTaskByFacilitator(ConfirmImcTaskDto confirmImcTaskDto);//服务商接单

    ImcTaskChangeStatusDto denyImcTaskByPrincipal(ImcTaskChangeStatusDto imcTaskChangeStatusDto);//否决一项巡检任务
//    List<ImcInspectionTask> getTaskByFacilitatorId(TaskQueryDto taskQueryDto);//根据服务商id查询对应的巡检任务
//
//    List<ImcInspectionTask> getTaskByFacilitatorIdAndStatus(TaskQueryDto taskQueryDto);//根据服务商id查询指定状态的巡检任务
}
