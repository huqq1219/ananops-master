package com.ananops.provider.service.hystrix;

import com.ananops.provider.model.dto.TaskDto;
import com.ananops.provider.model.dto.TaskQueryDto;
import com.ananops.provider.service.ImcTaskFeignApi;

import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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


}
