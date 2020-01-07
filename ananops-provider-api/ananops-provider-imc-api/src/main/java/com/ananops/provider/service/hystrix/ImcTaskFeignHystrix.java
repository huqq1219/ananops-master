package com.ananops.provider.service.hystrix;

import com.ananops.provider.model.dto.*;
import com.ananops.provider.service.ImcTaskFeignApi;

import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * Created by rongshuai on 2019/12/20 18:15
 */
@Component
public class ImcTaskFeignHystrix implements ImcTaskFeignApi {
    @Override
    public Wrapper<List<TaskDto>> getByFacilitatorId(@ApiParam(name = "getTaskByFacilitatorId",value = "根据服务商ID查询巡检任务")@RequestBody TaskQueryDto taskQueryDto){
        return null;
    }

    @Override
    public Wrapper<List<TaskDto>> getByFacilitatorIdAndStatus(@ApiParam(name = "getTaskByFacilitatorIdAndStatus",value = "根据服务商ID查询指定状态的巡检任务")@RequestBody TaskQueryDto taskQueryDto){
        return null;
    }


    @Override
    public Wrapper<List<TaskDto>> getByFacilitatorManagerId(@ApiParam(name = "getByFacilitatorManagerId",value = "根据服务商管理员ID查询巡检任务")@RequestBody TaskQueryDto taskQueryDto){
        return null;
    }

    @Override
    public Wrapper<List<TaskDto>> getByFacilitatorManagerIdAndStatus(@ApiParam(name = "getByFacilitatorManagerIdAndStatus",value = "根据服务商管理员ID查询指定状态的巡检任务")@RequestBody TaskQueryDto taskQueryDto){
        return null;
    }

    @Override
    public Wrapper<List<TaskDto>> getByFacilitatorGroupId(@ApiParam(name = "getByFacilitatorGroupId",value = "根据服务商组织ID查询巡检任务")@RequestBody TaskQueryDto taskQueryDto){
        return null;
    }

    @Override
    public Wrapper<List<TaskDto>> getByFacilitatorGroupIdAndStatus(@ApiParam(name = "getByFacilitatorGroupIdAndStatus",value = "根据服务商组织ID查询指定状态的巡检任务")@RequestBody TaskQueryDto taskQueryDto){
        return null;
    }

    @Override
    public Wrapper<ImcTaskChangeStatusDto> modifyTaskStatusByTaskId(@ApiParam(name = "modifyTaskStatusByTaskId",value = "根据巡检任务的ID修改该任务的状态")@RequestBody ImcTaskChangeStatusDto imcTaskChangeStatusDto){
        return null;
    }

    @Override
    public Wrapper<TaskDto> getTaskByTaskId(@ApiParam(name = "taskId",value = "根据巡检任务的ID获取巡检任务的详情")@RequestParam("taskId") Long taskId){
        return null;
    }

    @Override
    public Wrapper<TaskChangeFacilitatorDto> modifyFacilitatorByTaskId(@ApiParam(name = "modifyFacilitatorByTaskId",value = "修改巡检任务对应的服务商")@RequestBody TaskChangeFacilitatorDto taskChangeFacilitatorDto){
        return null;
    }

    @Override
    public Wrapper<ImcTaskChangeStatusDto> refuseImcTaskByTaskId(@ApiParam(name = "refuseImcTaskByTaskId",value = "服务商拒单（巡检任务）")@RequestBody RefuseTaskDto refuseTaskDto){
        return null;
    }
}
