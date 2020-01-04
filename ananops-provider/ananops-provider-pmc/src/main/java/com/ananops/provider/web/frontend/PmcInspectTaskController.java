package com.ananops.provider.web.frontend;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.BaseController;
import com.ananops.provider.model.domain.PmcInspectTask;
import com.ananops.provider.model.dto.PmcInspectTaskDto;
import com.ananops.provider.service.PmcInspectTaskService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created By ChengHao On 2019/11/28
 */
@RestController
@RequestMapping(value = "/InspectDevice",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB -PmcInspectDeviceController",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PmcInspectTaskController extends BaseController {
    @Autowired
    PmcInspectTaskService pmcInspectTaskService;

    @PostMapping("/save")
    @ApiOperation(httpMethod = "POST",value = "编辑巡检任务,当id为空时新增巡检任务,不为空时为更新巡检任务")
    public Wrapper save(@RequestBody PmcInspectTaskDto pmcInspectTaskDto){
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        PmcInspectTask pmcInspectTask = new PmcInspectTask();
        BeanUtils.copyProperties(pmcInspectTaskDto, pmcInspectTask);
        pmcInspectTaskService.saveDevice(pmcInspectTask,loginAuthDto);
        return WrapMapper.ok();
    }

    @PostMapping("/getTaskById/{id}")
    @ApiOperation(httpMethod = "POST",value = "根据id获取巡检任务")
    public Wrapper<PmcInspectTask> getTaskById(@PathVariable Long id){
        PmcInspectTask pmcInspectTask = pmcInspectTaskService.getTaskById(id);
        return WrapMapper.ok(pmcInspectTask);
    }

    @PostMapping("/getTasksByProjectId/{projectId}")
    @ApiOperation(httpMethod = "POST",value = "获取某个项目的巡检任务")
    public Wrapper<List<PmcInspectTask>> getTasksByProjectId(@PathVariable Long projectId){
        List<PmcInspectTask> pmcInspectTasks = pmcInspectTaskService.getTasksByProjectId(projectId);
        return WrapMapper.ok(pmcInspectTasks);
    }

    @PostMapping("deleteTaskById/{id}")
    @ApiOperation(httpMethod = "POST",value = "删除巡检任务")
    public Wrapper deleteTaskById(@PathVariable Long id){
        pmcInspectTaskService.deleteTaskById(id);
        return WrapMapper.ok();
    }

    @PostMapping("deleteTaskByProjectId/{projectId}")
    @ApiOperation(httpMethod = "POST",value = "删除巡检任务")
    public Wrapper deleteTaskByProjectId(@PathVariable Long projectId){
        pmcInspectTaskService.deleteTaskByProjectId(projectId);
        return WrapMapper.ok();
    }

}
