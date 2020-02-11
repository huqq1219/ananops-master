package com.ananops.provider.web.frontend;


import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.BaseController;

import com.ananops.provider.model.domain.MdmcTask;
import com.ananops.provider.model.domain.MdmcTaskLog;
import com.ananops.provider.model.dto.*;
import com.ananops.provider.service.MdmcTaskLogService;
import com.ananops.provider.service.MdmcTaskService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/mdmcTask",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - MdmcTask",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MdmcTaskController extends BaseController {
    @Resource
    MdmcTaskService taskService;

    @Resource
    MdmcTaskLogService taskLogService;

    @PostMapping(value = "/save")
    @ApiOperation(httpMethod = "POST",value = "创建维修任务记录")
    public Wrapper<MdmcAddTaskDto> saveTask(@ApiParam(name = "saveTask",value = "添加维修任务记录")@RequestBody MdmcAddTaskDto mdmcAddTaskDto){
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        return WrapMapper.ok(taskService.saveTask(mdmcAddTaskDto,loginAuthDto));
    }

    @PostMapping(value = "/modify")
    @ApiOperation(httpMethod = "POST",value = "修改维修任务记录")
    public Wrapper<MdmcTaskDto> modifyTask(@ApiParam(name = "modifyTask",value = "修改维修任务记录")@RequestBody MdmcTaskDto mdmcTaskDto){
        return WrapMapper.ok(taskService.modifyTask(mdmcTaskDto));
    }

    @GetMapping(value = "/getTaskLogs/{taskId}")
    @ApiOperation(httpMethod = "GET",value = "根据任务ID查询任务的日志")
    public Wrapper<List<MdmcTaskLog>> getTaskLogs(@PathVariable("taskId") Long taskId){
        List<MdmcTaskLog> taskLogList=taskLogService.getTaskLogsByTaskId(taskId);
        return WrapMapper.ok(taskLogList);
    }

    @PostMapping(value = "/modifyTaskStatusByTaskId/{taskId}")
    @ApiOperation(httpMethod = "POST",value = "更改任务的状态")
    public Wrapper<MdmcChangeStatusDto> modifyTaskStatusByTaskId(@ApiParam(name = "modifyTaskStatus",value = "根据任务的ID修改任务的状态")@RequestBody MdmcChangeStatusDto changeStatusDto){
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        taskService.modifyTaskStatus(changeStatusDto,loginAuthDto);
        return WrapMapper.ok(changeStatusDto);
    }

    @PostMapping(value = "/getTaskByTaskId")
    @ApiOperation(httpMethod = "POST",value = "根据任务的状态，获取工单列表")
    public Wrapper<List<MdmcTask>> getTaskByStatus(@RequestBody MdmcStatusDto statusDto){
        List<MdmcTask> taskList = taskService.getTaskListByStatus(statusDto);
        return WrapMapper.ok(taskList);
    }

    @GetMapping(value = "/getTaskByTaskId/{taskId}")
    @ApiOperation(httpMethod = "GET",value = "根据任务的ID，获取当前的任务详情")
    public Wrapper<MdmcTask> getTaskByTaskId(@PathVariable("taskId") Long taskId){
        MdmcTask task = taskService.getTaskByTaskId(taskId);
        return WrapMapper.ok(task);
    }
    
    @GetMapping(value = "/getTaskByTaskId")
    @ApiOperation(httpMethod = "GET",value = "根据任务的ID，获取当前的任务详情(param)")
    public Wrapper<MdmcTask> getTaskByTaskId2(@RequestParam("taskId") Long taskId){
        MdmcTask task = taskService.getTaskByTaskId(taskId);
        return WrapMapper.ok(task);
    }

//    @PostMapping(value = "/getTaskListByUserId")
//    @ApiOperation(httpMethod = "POST",value = "根据用户ID查询工单列表")
//    public Wrapper<List<MdmcTask>> getTaskListByUserId(@RequestBody MdmcStatusDto statusDto){
//        List<MdmcTask> taskList=taskService.getTaskListByUserId(statusDto);
//        return WrapMapper.ok(taskList);
//    }
//
//    @PostMapping(value = "/getTaskListByFacilitatorId")
//    @ApiOperation(httpMethod = "POST",value = "根据服务商ID查询工单列表")
//    public Wrapper<List<MdmcTask>> getTaskListByFacilitatorId(@RequestBody MdmcStatusDto statusDto){
//        List<MdmcTask> taskList=taskService.getTaskListByFacilitatorId(statusDto);
//        return WrapMapper.ok(taskList);
//    }
//
//    @PostMapping(value = "/getTaskListByPrincipalId")
//    @ApiOperation(httpMethod = "POST",value = "根据甲方ID查询工单列表")
//    public Wrapper<List<MdmcTask>> getTaskListByPrincipalId(@RequestBody MdmcStatusDto statusDto){
//        List<MdmcTask> taskList=taskService.getTaskListByPrincipalId(statusDto);
//        return WrapMapper.ok(taskList);
//    }
//    @PostMapping(value = "/getTaskListByMaintainerId")
//    @ApiOperation(httpMethod = "POST",value = "根据维修工ID查询工单列表")
//    public Wrapper<List<MdmcTask>> getTaskListByMaintainerId(@RequestBody MdmcStatusDto statusDto){
//        List<MdmcTask> taskList=taskService.getTaskListByMaintainerId(statusDto);
//        return WrapMapper.ok(taskList);
//    }
    @PostMapping(value = "/getAllTaskList")
    @ApiOperation(httpMethod = "POST",value = "返回全部工单列表")
    public Wrapper<List<MdmcTask>> getTaskList(@RequestBody MdmcStatusDto statusDto){
        List<MdmcTask> taskList=taskService.getTaskList(statusDto);
        return WrapMapper.ok(taskList);
    }

//    @PostMapping(value = "/getTaskByProjectId")
//    @ApiOperation(httpMethod = "POST",value = "根据项目id返回工单列表")
//    public Wrapper<List<MdmcTask>> getTaskListByProjectId(@RequestBody MdmcStatusDto statusDto){
//        List<MdmcTask> taskList=taskService.getTaskListByProjectId(statusDto);
//        return WrapMapper.ok(taskList);
//    }
//    @PostMapping(value = "/getTaskListByUserIdAndStatus")
//    @ApiOperation(httpMethod = "POST",value = "根据用户id和状态查询工单列表")
//    public Wrapper<List<MdmcTask>> getTaskListByUserIdAndStatus(@RequestBody MdmcStatusDto statusDto){
//        List<MdmcTask> taskList=taskService.getTaskListByUserIdAndStatus(statusDto);
//        return WrapMapper.ok(taskList);
//    }
//
//    @PostMapping(value = "/getTaskListByMaintainerIdAndStatus")
//    @ApiOperation(httpMethod = "POST",value = "根据维修工id和状态查询工单列表")
//    public Wrapper<List<MdmcTask>> getTaskListByMaintainerIdAndStatus(@RequestBody MdmcStatusDto statusDto){
//        List<MdmcTask> taskList=taskService.getTaskListByMaintainerIdAndStatus(statusDto);
//        return WrapMapper.ok(taskList);
//    }
//
//    @PostMapping(value = "/getTaskListByFacilitatorIdAndStatus")
//    @ApiOperation(httpMethod = "POST",value = "根据服务商id和状态查询工单列表")
//    public Wrapper<List<MdmcTask>> getTaskListByFacilitatorIdAndStatus(@RequestBody MdmcStatusDto statusDto){
//        List<MdmcTask> taskList=taskService.getTaskListByFacilitatorIdAndStatus(statusDto);
//        return WrapMapper.ok(taskList);
//    }
//
//    @PostMapping(value = "/getTaskListByPrincipalIdAndStatus")
//    @ApiOperation(httpMethod = "POST",value = "根据甲方id和状态查询工单列表")
//    public Wrapper<List<MdmcTask>> getTaskListByPrincipalIdAndStatus(@RequestBody MdmcStatusDto statusDto){
//        List<MdmcTask> taskList=taskService.getTaskListByPrincipalIdAndStatus(statusDto);
//        return WrapMapper.ok(taskList);
//    }

    @PostMapping(value = "/getTaskListByIdAndStatus")
    @ApiOperation(httpMethod = "POST",value = "根据id和状态查询列表")
    public Wrapper<List<MdmcTask>> getTaskListByIdAndStatus(@RequestBody MdmcQueryDto queryDto){
        List<MdmcTask> taskList=taskService.getTaskListByIdAndStatus(queryDto);
        return WrapMapper.ok(taskList);
    }

    @PostMapping(value = "/getTaskListByIdAndStatusArrary")
    @ApiOperation(httpMethod = "POST",value = "根据id和状态数组查询列表")
    public Wrapper<List<MdmcListDto>> getTaskListByIdAndStatusArrary(@RequestBody MdmcStatusArrayDto statusArrayDto){
        List<MdmcListDto> listDtoList=taskService.getTaskListByIdAndStatusArrary(statusArrayDto);
        return WrapMapper.ok(listDtoList);
    }

    @PostMapping(value = "/getTaskList")
    @ApiOperation(httpMethod = "POST",value = "分页查询列表")
    public Wrapper<MdmcPageDto> getTaskList(@RequestBody MdmcQueryDto queryDto){
        MdmcPageDto pageDto=taskService.getTaskListByPage(queryDto);
        return WrapMapper.ok(pageDto);
    }

    @PostMapping(value = "/refuseTaskByFacilitator")
    @ApiOperation(httpMethod = "POST",value = "服务商拒单")
    public Wrapper<MdmcChangeStatusDto> refuseTaskByFacilitator(@RequestBody RefuseMdmcTaskDto refuseMdmcTaskDto){
        return WrapMapper.ok(taskService.refuseTaskByFacilitator(refuseMdmcTaskDto));
    }

    @PostMapping(value = "/refuseTaskByMaintainer")
    @ApiOperation(httpMethod = "POST",value = "工程师拒单")
    public Wrapper<MdmcChangeStatusDto> refuseTaskByMaintainer(@RequestBody RefuseMdmcTaskDto refuseMdmcTaskDto){
        return WrapMapper.ok(taskService.refuseTaskByMaintainer(refuseMdmcTaskDto));
    }
}
