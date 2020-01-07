package com.ananops.provider.service;

import com.ananops.provider.model.dto.*;
import com.ananops.provider.service.hystrix.ImcTaskFeignHystrix;
import com.ananops.security.feign.OAuth2FeignAutoConfiguration;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by rongshuai on 2019/12/20 18:14
 */
@FeignClient(value = "ananops-provider-imc", configuration = OAuth2FeignAutoConfiguration.class, fallback = ImcTaskFeignHystrix.class)
public interface ImcTaskFeignApi {

    @PostMapping(value = "/api/task/getByFacilitatorId")
    Wrapper<List<TaskDto>> getByFacilitatorId(@ApiParam(name = "getTaskByFacilitatorId",value = "根据服务商ID查询巡检任务")@RequestBody TaskQueryDto taskQueryDto);

    @PostMapping(value = "/api/task/getByFacilitatorIdAndStatus")
    Wrapper<List<TaskDto>> getByFacilitatorIdAndStatus(@ApiParam(name = "getTaskByFacilitatorIdAndStatus",value = "根据服务商ID查询指定状态的巡检任务")@RequestBody TaskQueryDto taskQueryDto);

    @PostMapping(value = "/api/task/getByFacilitatorManagerId")
    Wrapper<List<TaskDto>> getByFacilitatorManagerId(@ApiParam(name = "getByFacilitatorManagerId",value = "根据服务商管理员ID查询巡检任务")@RequestBody TaskQueryDto taskQueryDto);

    @PostMapping(value = "/api/task/getByFacilitatorManagerIdAndStatus")
    Wrapper<List<TaskDto>> getByFacilitatorManagerIdAndStatus(@ApiParam(name = "getByFacilitatorManagerIdAndStatus",value = "根据服务商管理员ID查询指定状态的巡检任务")@RequestBody TaskQueryDto taskQueryDto);

    @PostMapping(value = "/api/task/getByFacilitatorGroupId")
    Wrapper<List<TaskDto>> getByFacilitatorGroupId(@ApiParam(name = "getByFacilitatorGroupId",value = "根据服务商组织ID查询巡检任务")@RequestBody TaskQueryDto taskQueryDto);

    @PostMapping(value = "/api/task/getByFacilitatorGroupIdAndStatus")
    Wrapper<List<TaskDto>> getByFacilitatorGroupIdAndStatus(@ApiParam(name = "getByFacilitatorGroupIdAndStatus",value = "根据服务商组织ID查询指定状态的巡检任务")@RequestBody TaskQueryDto taskQueryDto);

    @PostMapping(value = "/api/task/modifyTaskStatusByTaskId")
    Wrapper<ImcTaskChangeStatusDto> modifyTaskStatusByTaskId(@ApiParam(name = "modifyTaskStatusByTaskId",value = "根据巡检任务的ID修改该任务的状态")@RequestBody ImcTaskChangeStatusDto imcTaskChangeStatusDto);

    @PostMapping(value = "/api/task/getTaskByTaskId")
    Wrapper<TaskDto> getTaskByTaskId(@ApiParam(name = "taskId",value = "根据巡检任务的ID获取巡检任务的详情")@RequestParam("taskId") Long taskId);

    @PostMapping(value = "/api/task/modifyFacilitatorByTaskId")
    Wrapper<TaskChangeFacilitatorDto> modifyFacilitatorByTaskId(@ApiParam(name = "modifyFacilitatorByTaskId",value = "修改巡检任务对应的服务商")@RequestBody TaskChangeFacilitatorDto taskChangeFacilitatorDto);

    @PostMapping(value = "/api/task/refuseImcTaskByTaskId")
    Wrapper<ImcTaskChangeStatusDto> refuseImcTaskByTaskId(@ApiParam(name = "refuseImcTaskByTaskId",value = "服务商拒单（巡检任务）")@RequestBody RefuseTaskDto refuseTaskDto);
}
