package com.ananops.provider.web.rpc;

import com.ananops.core.support.BaseController;
import com.ananops.provider.mapper.ImcInspectionItemMapper;
import com.ananops.provider.mapper.ImcInspectionTaskMapper;
import com.ananops.provider.model.domain.ImcInspectionItem;
import com.ananops.provider.model.domain.ImcInspectionTask;
import com.ananops.provider.model.dto.ItemDto;
import com.ananops.provider.model.dto.TaskDto;
import com.ananops.provider.model.dto.TaskQueryDto;
import com.ananops.provider.service.ImcInspectionItemService;
import com.ananops.provider.service.ImcInspectionTaskLogService;
import com.ananops.provider.service.ImcInspectionTaskService;
import com.ananops.provider.service.ImcTaskFeignApi;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rongshuai on 2019/12/20 18:13
 */
@RefreshScope
@RestController
@Api(value = "API - ImcTaskFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ImcTaskFeignClient extends BaseController implements ImcTaskFeignApi {
    @Resource
    ImcInspectionTaskService imcInspectionTaskService;

    @Resource
    ImcInspectionTaskLogService imcInspectionTaskLogService;

    @Resource
    ImcInspectionTaskMapper imcInspectionTaskMapper;

    @Resource
    ImcInspectionItemMapper imcInspectionItemMapper;

    @Override
    @ApiOperation(httpMethod = "POST", value = "根据服务商ID查询巡检任务")
    public Wrapper<List<TaskDto>> getByFacilitatorId(@ApiParam(name = "getTaskByFacilitatorId",value = "根据服务商ID查询巡检任务")@RequestBody TaskQueryDto taskQueryDto){
        List<TaskDto> taskDtoList = new ArrayList<>();
        List<ImcInspectionTask> imcInspectionTaskList = imcInspectionTaskService.getTaskByFacilitatorId(taskQueryDto);
        imcInspectionTaskList.forEach(imcInspectionTask -> {
            Long taskId = imcInspectionTask.getId();
            TaskDto taskDto = new TaskDto();
            BeanUtils.copyProperties(imcInspectionTask,taskDto);
            taskDto.setItemDtoList(this.getItemList(taskId));
            taskDtoList.add(taskDto);
        });
        return WrapMapper.ok(taskDtoList);
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "根据服务商ID查询指定状态的巡检任务")
    public Wrapper<List<TaskDto>> getByFacilitatorIdAndStatus(@ApiParam(name = "getTaskByFacilitatorIdAndStatus",value = "根据服务商ID查询指定状态的巡检任务")@RequestBody TaskQueryDto taskQueryDto){
        List<TaskDto> taskDtoList = new ArrayList<>();
        List<ImcInspectionTask> imcInspectionTaskList = imcInspectionTaskService.getTaskByFacilitatorIdAndStatus(taskQueryDto);
        imcInspectionTaskList.forEach(imcInspectionTask -> {
            Long taskId = imcInspectionTask.getId();
            TaskDto taskDto = new TaskDto();
            BeanUtils.copyProperties(imcInspectionTask,taskDto);
            taskDto.setItemDtoList(this.getItemList(taskId));
            taskDtoList.add(taskDto);
        });
        return WrapMapper.ok(taskDtoList);
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "根据服务商管理员ID查询巡检任务")
    public Wrapper<List<TaskDto>> getByFacilitatorManagerId(@ApiParam(name = "getByFacilitatorManagerId",value = "根据服务商管理员ID查询巡检任务")@RequestBody TaskQueryDto taskQueryDto){
        List<TaskDto> taskDtoList = new ArrayList<>();
        PageHelper.startPage(taskQueryDto.getPageNum(),taskQueryDto.getPageSize());
        String taskName = taskQueryDto.getTaskName();
        List<ImcInspectionTask> imcInspectionTaskList;
        if(StringUtils.isNotBlank(taskName)) {
            taskName = "%" + taskName + "%";
            imcInspectionTaskList = imcInspectionTaskMapper.queryTaskByFacilitatorManagerIdAndTaskName(taskQueryDto.getUserId(),taskName);
        }else{
            imcInspectionTaskList = imcInspectionTaskMapper.queryTaskByFacilitatorManagerId(taskQueryDto.getUserId());
        }
        imcInspectionTaskList.forEach(imcInspectionTask -> {
            Long taskId = imcInspectionTask.getId();
            TaskDto taskDto = new TaskDto();
            BeanUtils.copyProperties(imcInspectionTask,taskDto);
            taskDto.setItemDtoList(this.getItemList(taskId));
            taskDtoList.add(taskDto);
        });
        return WrapMapper.ok(taskDtoList);
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "根据服务商管理员ID查询指定状态的巡检任务")
    public Wrapper<List<TaskDto>> getByFacilitatorManagerIdAndStatus(@ApiParam(name = "getByFacilitatorManagerIdAndStatus",value = "根据服务商管理员ID查询指定状态的巡检任务")@RequestBody TaskQueryDto taskQueryDto){
        List<TaskDto> taskDtoList = new ArrayList<>();
        PageHelper.startPage(taskQueryDto.getPageNum(),taskQueryDto.getPageSize());
        String taskName = taskQueryDto.getTaskName();
        List<ImcInspectionTask> imcInspectionTaskList;
        if(StringUtils.isNotBlank(taskName)) {
            taskName = "%" + taskName + "%";
            imcInspectionTaskList = imcInspectionTaskMapper.queryTaskByFacilitatorManagerIdAndStatusAndTaskName(taskQueryDto.getUserId(),taskQueryDto.getStatus(),taskName);
        }else{
            imcInspectionTaskList = imcInspectionTaskMapper.queryTaskByFacilitatorManagerIdAndStatus(taskQueryDto.getUserId(),taskQueryDto.getStatus());
        }
        imcInspectionTaskList.forEach(imcInspectionTask -> {
            Long taskId = imcInspectionTask.getId();
            TaskDto taskDto = new TaskDto();
            BeanUtils.copyProperties(imcInspectionTask,taskDto);
            taskDto.setItemDtoList(this.getItemList(taskId));
            taskDtoList.add(taskDto);
        });
        return WrapMapper.ok(taskDtoList);
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "根据服务商组织ID查询巡检任务")
    public Wrapper<List<TaskDto>> getByFacilitatorGroupId(@ApiParam(name = "getByFacilitatorGroupId",value = "根据服务商组织ID查询巡检任务")@RequestBody TaskQueryDto taskQueryDto){
        List<TaskDto> taskDtoList = new ArrayList<>();
        PageHelper.startPage(taskQueryDto.getPageNum(),taskQueryDto.getPageSize());
        String taskName = taskQueryDto.getTaskName();
        List<ImcInspectionTask> imcInspectionTaskList;
        if(StringUtils.isNotBlank(taskName)) {
            taskName = "%" + taskName + "%";
            imcInspectionTaskList = imcInspectionTaskMapper.queryTaskByFacilitatorGroupIdAndTaskName(taskQueryDto.getUserId(),taskName);
        }else{
            imcInspectionTaskList = imcInspectionTaskMapper.queryTaskByFacilitatorGroupId(taskQueryDto.getUserId());
        }
        imcInspectionTaskList.forEach(imcInspectionTask -> {
            Long taskId = imcInspectionTask.getId();
            TaskDto taskDto = new TaskDto();
            BeanUtils.copyProperties(imcInspectionTask,taskDto);
            taskDto.setItemDtoList(this.getItemList(taskId));
            taskDtoList.add(taskDto);
        });
        return WrapMapper.ok(taskDtoList);
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "根据服务商组织ID查询指定状态的巡检任务")
    public Wrapper<List<TaskDto>> getByFacilitatorGroupIdAndStatus(@ApiParam(name = "getByFacilitatorGroupIdAndStatus",value = "根据服务商组织ID查询指定状态的巡检任务")@RequestBody TaskQueryDto taskQueryDto){
        List<TaskDto> taskDtoList = new ArrayList<>();
        PageHelper.startPage(taskQueryDto.getPageNum(),taskQueryDto.getPageSize());
        String taskName = taskQueryDto.getTaskName();
        List<ImcInspectionTask> imcInspectionTaskList;
        if(StringUtils.isNotBlank(taskName)) {
            taskName = "%" + taskName + "%";
            imcInspectionTaskList = imcInspectionTaskMapper.queryTaskByFacilitatorGroupIdAndStatusAndTaskName(taskQueryDto.getUserId(),taskQueryDto.getStatus(),taskName);
        }else{
            imcInspectionTaskList = imcInspectionTaskMapper.queryTaskByFacilitatorGroupIdAndStatus(taskQueryDto.getUserId(),taskQueryDto.getStatus());
        }
        imcInspectionTaskList.forEach(imcInspectionTask -> {
            Long taskId = imcInspectionTask.getId();
            TaskDto taskDto = new TaskDto();
            BeanUtils.copyProperties(imcInspectionTask,taskDto);
            taskDto.setItemDtoList(this.getItemList(taskId));
            taskDtoList.add(taskDto);
        });
        return WrapMapper.ok(taskDtoList);
    }

    public List<ItemDto> getItemList(Long taskId){
        Example example = new Example(ImcInspectionItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("inspectionTaskId",taskId);
        List<ImcInspectionItem> imcInspectionItemList = imcInspectionItemMapper.selectByExample(example);
        List<ItemDto> itemDtoList = new ArrayList<>();
        imcInspectionItemList.forEach(imcInspectionItem -> {
            ItemDto itemDto = new ItemDto();
            BeanUtils.copyProperties(imcInspectionItem,itemDto);
            itemDtoList.add(itemDto);
        });
        return itemDtoList;
    }
}
