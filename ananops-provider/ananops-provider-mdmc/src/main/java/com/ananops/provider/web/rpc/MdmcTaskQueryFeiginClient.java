package com.ananops.provider.web.rpc;

import com.ananops.core.support.BaseController;
import com.ananops.provider.model.domain.MdmcTask;
import com.ananops.provider.model.dto.*;
import com.ananops.provider.service.MdmcTaskFeignApi;
import com.ananops.provider.service.MdmcTaskLogService;
import com.ananops.provider.service.MdmcTaskService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.List;

@RefreshScope
@RestController
@Api(value = "API - MdmcQueryFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MdmcTaskQueryFeiginClient extends BaseController implements MdmcTaskFeignApi {
    
    @Resource
    MdmcTaskService taskService;
    
    
    @ApiOperation(httpMethod = "POST",value = "根据任务的状态，获取工单列表")
    public Wrapper<List<MdmcTask>> getTaskByStatus(@RequestBody MdmcStatusDto statusDto){
        List<MdmcTask> taskList = taskService.getTaskListByStatus(statusDto);
        return WrapMapper.ok(taskList);
    }
    
    @ApiOperation(httpMethod = "POST",value = "根据任务的ID，获取当前的任务详情")
    public Wrapper<MdmcTask> getTaskByTaskId(@PathVariable("taskId") Long taskId){
        MdmcTask task = taskService.getTaskByTaskId(taskId);
        return WrapMapper.ok(task);
    }
    
    @ApiOperation(httpMethod = "POST",value = "返回全部工单列表")
    public Wrapper<List<MdmcTask>> getTaskList(@RequestBody MdmcStatusDto statusDto){
        List<MdmcTask> taskList=taskService.getTaskList(statusDto);
        return WrapMapper.ok(taskList);
    }
    
    @ApiOperation(httpMethod = "POST",value = "根据id和状态查询列表")
    public Wrapper<List<MdmcTask>> getTaskListByIdAndStatus(@RequestBody MdmcQueryDto queryDto){
        List<MdmcTask> taskList=taskService.getTaskListByIdAndStatus(queryDto);
        return WrapMapper.ok(taskList);
    }
    
    @ApiOperation(httpMethod = "POST",value = "根据id和状态数组查询列表")
    public Wrapper<List<MdmcListDto>> getTaskListByIdAndStatusArrary(@RequestBody MdmcStatusArrayDto statusArrayDto){
        List<MdmcListDto> listDtoList=taskService.getTaskListByIdAndStatusArrary(statusArrayDto);
        return WrapMapper.ok(listDtoList);
    }
    
    @ApiOperation(httpMethod = "POST",value = "分页查询列表")
    public Wrapper<MdmcPageDto> getTaskList(@RequestBody MdmcQueryDto queryDto){
        MdmcPageDto pageDto=taskService.getTaskListByPage(queryDto);
        return WrapMapper.ok(pageDto);
    }
}
