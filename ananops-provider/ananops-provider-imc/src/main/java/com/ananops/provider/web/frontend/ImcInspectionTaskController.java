package com.ananops.provider.web.frontend;


import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.BaseController;
import com.ananops.provider.core.annotation.AnanLogAnnotation;
import com.ananops.provider.model.domain.ImcInspectionTask;
import com.ananops.provider.model.dto.*;
import com.ananops.provider.model.vo.TaskLogVo;
import com.ananops.provider.service.ImcInspectionTaskLogService;
import com.ananops.provider.service.ImcInspectionTaskService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rongshuai on 2019/11/27 19:28
 */
@RestController
@RequestMapping(value = "/inspectionTask",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - ImcInspectionTask",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ImcInspectionTaskController extends BaseController {
    @Resource
    ImcInspectionTaskService imcInspectionTaskService;

    @Resource
    ImcInspectionTaskLogService imcInspectionTaskLogService;

    @PostMapping(value = "/save")
    @ApiOperation(httpMethod = "POST",value = "编辑巡检任务记录")
    @AnanLogAnnotation
    public Wrapper<ImcAddInspectionTaskDto> saveInspectionTask(@ApiParam(name = "saveInspectionTask",value = "编辑巡检任务记录")@RequestBody ImcAddInspectionTaskDto imcAddInspectionTaskDto){
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        return WrapMapper.ok(imcInspectionTaskService.saveTask(imcAddInspectionTaskDto,loginAuthDto));
    }

    @GetMapping(value = "/getTaskByTaskId/{taskId}")
    @ApiOperation(httpMethod = "GET",value = "根据任务的ID，获取当前的任务详情")
    public Wrapper<ImcInspectionTask> getTaskByTaskId(@PathVariable Long taskId){
        ImcInspectionTask imcInspectionTask = imcInspectionTaskService.getTaskByTaskId(taskId);
        return WrapMapper.ok(imcInspectionTask);
    }

    @PostMapping(value = "/modifyTaskStatusByTaskId/{taskId}")
    @ApiOperation(httpMethod = "POST",value = "更改巡检任务的状态")
    @AnanLogAnnotation
    public Wrapper<ImcTaskChangeStatusDto> modifyTaskStatusByTaskId(@ApiParam(name = "modifyTaskStatus",value = "根据巡检任务的ID修改巡检任务的状态")@RequestBody ImcTaskChangeStatusDto imcTaskChangeStatusDto){
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        imcInspectionTaskService.modifyTaskStatus(imcTaskChangeStatusDto,loginAuthDto);
        return WrapMapper.ok(imcTaskChangeStatusDto);
    }

    @PostMapping(value = "/getTaskLogs")
    @ApiOperation(httpMethod = "POST",value = "根据巡检任务ID查询任务的日志")
    public Wrapper<List<TaskLogVo>> getTaskLogs(@ApiParam(name = "getTaskLogsByTaskId",value = "根据巡检任务ID查询任务的日志")@RequestBody TaskLogQueryDto taskLogQueryDto){
        return WrapMapper.ok(imcInspectionTaskLogService.getTaskLogsByTaskId(taskLogQueryDto));
    }

    @PostMapping(value = "/getTaskByStatus")
    @ApiOperation(httpMethod = "POST",value = "根据巡检任务的状态查询对应的任务")
    public Wrapper<List<ImcInspectionTask>> getTaskByStatus(@ApiParam(name = "getTaskByStatus",value = "根据巡检任务的状态查询巡检任务")@RequestBody TaskQueryDto taskQueryDto){
        return WrapMapper.ok(imcInspectionTaskService.getTaskByStatus(taskQueryDto));
    }

    @PostMapping(value = "/modifyTaskName")
    @ApiOperation(httpMethod = "POST",value = "修改巡检任务的名字")
    @AnanLogAnnotation
    public Wrapper<TaskNameChangeDto> modifyTaskName(@ApiParam(name = "modifyTaskName",value = "修改巡检任务的名字")@RequestBody TaskNameChangeDto taskNameChangeDto){
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        imcInspectionTaskService.modifyTaskName(taskNameChangeDto,loginAuthDto);
        return WrapMapper.ok(taskNameChangeDto);
    }

    @PostMapping(value = "/getTaskByProjectId")
    @ApiOperation(httpMethod = "POST",value = "根据项目查询对应的所有巡检任务")
    public Wrapper<List<ImcInspectionTask>> getTaskByProjectId(@ApiParam(name = "getTaskByProjectId",value = "根据项目ID查询巡检任务")@RequestBody TaskQueryDto taskQueryDto){
        return WrapMapper.ok(imcInspectionTaskService.getTaskByProjectId(taskQueryDto));
    }

    @PostMapping(value = "/getTaskByUserId")
    @ApiOperation(httpMethod = "POST",value = "根据用户（1：甲方，2：服务商，3：服务商管理员，4：服务商组织）的id查询对应的巡检任务")
    public Wrapper<List<ImcInspectionTask>> getTaskByUserId(@ApiParam(name = "getTaskByUserId",value = "根据用户（1：甲方，2：服务商）的ID查询巡检任务")@RequestBody TaskQueryDto taskQueryDto){
        return WrapMapper.ok(imcInspectionTaskService.getTaskByUserId(taskQueryDto));
    }

    @PostMapping(value = "/getTaskByUserIdAndStatus")
    @ApiOperation(httpMethod = "POST",value = "根据用户（1：甲方，2：服务商，3：服务商管理员，4：服务商组织）id查询指定状态的巡检任务")
    public Wrapper<List<ImcInspectionTask>> getTaskByUserIdAndStatus(@ApiParam(name = "getTaskByUserIdAndStatus",value = "根据用户（1：甲方，2：服务商）id查询指定状态的巡检任务")@RequestBody TaskQueryDto taskQueryDto){
        return WrapMapper.ok(imcInspectionTaskService.getTaskByUserIdAndStatus(taskQueryDto));
    }

    @PostMapping(value = "/modifyFacilitatorIdByTaskId")
    @ApiOperation(httpMethod = "POST",value = "根据巡检任务的ID修改对应任务的服务商ID")
    public Wrapper<TaskChangeFacilitatorDto> modifyFacilitatorIdByTaskId(@ApiParam(name = "modifyFacilitatorByTaskId",value = "根据巡检任务的ID修改对应任务的服务商ID")@RequestBody TaskChangeFacilitatorDto taskChangeFacilitatorDto){
        return WrapMapper.ok(imcInspectionTaskService.modifyFacilitator(taskChangeFacilitatorDto));
    }
//    @PostMapping(value = "/getTaskByFacilitatorId")
//    @ApiOperation(httpMethod = "POST",value = "根据服务商id查询巡检任务")
//    public Wrapper<List<ImcInspectionTask>> getTaskByFacilitatorId(@ApiParam(name = "getTaskByFacilitatorId",value = "根据服务商id查询巡检任务")@RequestBody TaskQueryDto taskQueryDto){
//        return WrapMapper.ok(imcInspectionTaskService.getTaskByFacilitatorId(taskQueryDto));
//    }
//
//    @PostMapping(value = "/getTaskByFacilitatorIdAndStatus")
//    @ApiOperation(httpMethod = "POST",value = "根据服务商id查询指定状态的巡检任务")
//    public Wrapper<List<ImcInspectionTask>> getTaskByFacilitatorIdAndStatus(@ApiParam(name = "getTaskByFacilitatorIdAndStatus",value = "根据服务商id查询指定状态的巡检任务")@RequestBody TaskQueryDto taskQueryDto){
//        return WrapMapper.ok(imcInspectionTaskService.getTaskByFacilitatorIdAndStatus(taskQueryDto));
//    }
}
