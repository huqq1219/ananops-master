package com.ananops.provider.web.rpc;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.base.exception.BusinessException;
import com.ananops.core.support.BaseController;
import com.ananops.provider.core.annotation.AnanLogAnnotation;
import com.ananops.provider.mapper.ImcInspectionItemMapper;
import com.ananops.provider.mapper.ImcInspectionTaskMapper;
import com.ananops.provider.model.domain.ImcInspectionItem;
import com.ananops.provider.model.domain.ImcInspectionTask;
import com.ananops.provider.model.dto.*;
import com.ananops.provider.model.enums.TaskStatusEnum;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Override
    @ApiOperation(httpMethod = "POST", value = "根据巡检任务的ID修改任务的状态")
    @AnanLogAnnotation
    public Wrapper<ImcTaskChangeStatusDto> modifyTaskStatusByTaskId(@ApiParam(name = "modifyTaskStatusByTaskId",value = "根据巡检任务的ID修改该任务的状态")@RequestBody ImcTaskChangeStatusDto imcTaskChangeStatusDto){
        LoginAuthDto loginAuthDto = imcTaskChangeStatusDto.getLoginAuthDto();
        imcInspectionTaskService.modifyTaskStatus(imcTaskChangeStatusDto,loginAuthDto);
        return WrapMapper.ok(imcTaskChangeStatusDto);
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "根据巡检任务的ID查看巡检任务的详情")
    public Wrapper<TaskDto> getTaskByTaskId(@ApiParam(name = "taskId",value = "根据巡检任务的ID获取巡检任务的详情")@RequestParam("taskId") Long taskId){
        TaskDto taskDto = new TaskDto();
        ImcInspectionTask imcInspectionTask = imcInspectionTaskService.getTaskByTaskId(taskId);
        BeanUtils.copyProperties(imcInspectionTask,taskDto);
        List<ItemDto> itemDtoList = getItemList(taskId);
        taskDto.setItemDtoList(itemDtoList);
        return WrapMapper.ok(taskDto);
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "修改巡检任务对应的服务商")
    public Wrapper<TaskChangeFacilitatorDto> modifyFacilitatorByTaskId(@ApiParam(name = "modifyFacilitatorByTaskId",value = "修改巡检任务对应的服务商")@RequestBody TaskChangeFacilitatorDto taskChangeFacilitatorDto){
        Long taskId = taskChangeFacilitatorDto.getTaskId();
        Long facilitatorId = taskChangeFacilitatorDto.getFacilitatorId();
        Example example = new Example(ImcInspectionTask.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",taskId);
        if(imcInspectionTaskMapper.selectCountByExample(example)==0){
            throw new BusinessException(ErrorCodeEnum.GL9999098);
        }
        ImcInspectionTask imcInspectionTask = imcInspectionTaskService.getTaskByTaskId(taskId);
        imcInspectionTask.setFacilitatorId(facilitatorId);
        int result = imcInspectionTaskService.update(imcInspectionTask);
        if(result == 1){
            return WrapMapper.ok(taskChangeFacilitatorDto);
        }
        throw new BusinessException(ErrorCodeEnum.GL9999093);
    }


    @Override
    @ApiOperation(httpMethod = "POST", value = "服务商拒单（巡检任务）")
    public Wrapper<ImcTaskChangeStatusDto> refuseImcTaskByTaskId(@ApiParam(name = "refuseImcTaskByTaskId",value = "服务商拒单（巡检任务）")@RequestBody RefuseTaskDto refuseTaskDto){
        LoginAuthDto loginAuthDto = refuseTaskDto.getLoginAuthDto();
        Long taskId = refuseTaskDto.getTaskId();
        ImcTaskChangeStatusDto imcTaskChangeStatusDto = new ImcTaskChangeStatusDto();
        imcTaskChangeStatusDto.setStatusMsg(TaskStatusEnum.getStatusMsg(TaskStatusEnum.WAITING_FOR_FACILITATOR.getStatusNum()));
        imcTaskChangeStatusDto.setStatus(TaskStatusEnum.WAITING_FOR_FACILITATOR.getStatusNum());
        imcTaskChangeStatusDto.setTaskId(taskId);
        Example example = new Example(ImcInspectionTask.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",taskId);
        if(imcInspectionTaskMapper.selectCountByExample(example)==0){
            //如果当前任务不存在
            throw new BusinessException(ErrorCodeEnum.GL9999098);
        }
        ImcInspectionTask imcInspectionTask = imcInspectionTaskMapper.selectByExample(example).get(0);
        if(imcInspectionTask.getStatus().equals(TaskStatusEnum.WAITING_FOR_ACCEPT.getStatusNum())){
            //如果当前任务的状态是等待服务商接单，才允许服务商拒单
            imcInspectionTask.setStatus(TaskStatusEnum.WAITING_FOR_FACILITATOR.getStatusNum());
            imcInspectionTask.setUpdateInfo(loginAuthDto);
            imcInspectionTaskMapper.updateByPrimaryKeySelective(imcInspectionTask);
        }else{
            throw new BusinessException(ErrorCodeEnum.GL9999086);
        }
        return WrapMapper.ok(imcTaskChangeStatusDto);
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
