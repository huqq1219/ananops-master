package com.ananops.provider.service;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.IService;
import com.ananops.provider.model.domain.MdmcReview;
import com.ananops.provider.model.domain.MdmcTask;
import com.ananops.provider.model.domain.MdmcTaskLog;
import com.ananops.provider.model.dto.*;

import java.util.List;


public interface MdmcTaskService extends IService<MdmcTask> {



    MdmcTask getTaskByTaskId(Long taskId);


    MdmcAddTaskDto saveTask(MdmcAddTaskDto mdmcAddTaskDto, LoginAuthDto loginAuthDto);

    MdmcTaskDto modifyTask(MdmcTaskDto mdmcTaskDto);


    MdmcTask modifyTaskStatus(MdmcChangeStatusDto changeStatusDto, LoginAuthDto loginAuthDto);

    Void FacilitatorTransfer();

    Void MaintainerTransfer();

//    List<MdmcTask> getTaskListByUserId(MdmcStatusDto statusDto);
//
//    List<MdmcTask> getTaskListByMaintainerId(MdmcStatusDto statusDto);
//
//    List<MdmcTask> getTaskListByFacilitatorId(MdmcStatusDto statusDto);
//
//    List<MdmcTask> getTaskListByPrincipalId(MdmcStatusDto statusDto);

    List<MdmcTask> getTaskListByStatus(MdmcStatusDto statusDto);

    List<MdmcTask> getTaskList(MdmcStatusDto statusDto);

//    List<MdmcTask> getTaskListByProjectId(MdmcStatusDto statusDto);
//
//    List<MdmcTask> getTaskListByUserIdAndStatus(MdmcStatusDto statusDto);
//
//    List<MdmcTask> getTaskListByMaintainerIdAndStatus(MdmcStatusDto statusDto);
//
//    List<MdmcTask> getTaskListByFacilitatorIdAndStatus(MdmcStatusDto statusDto);
//
//    List<MdmcTask> getTaskListByPrincipalIdAndStatus(MdmcStatusDto statusDto);

    List<MdmcTask> getTaskListByIdAndStatus(MdmcQueryDto queryDto);

    List<MdmcListDto> getTaskListByIdAndStatusArrary(MdmcStatusArrayDto statusArrayDto);

    MdmcPageDto getTaskListByPage(MdmcQueryDto queryDto);
}
