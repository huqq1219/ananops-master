package com.ananops.provider.web.rpc;

import com.ananops.core.support.BaseController;
import com.ananops.provider.service.ActivitiFeignApi;
import com.ananops.provider.service.dto.ApproSubmitDto;
import com.ananops.provider.service.impl.ActivitiServiceImpl;
import com.ananops.provider.service.impl.ApproveServiceImpl;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.activiti.engine.task.Task;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(value = "API - ActivitiFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ActivitiFeignClient extends BaseController implements ActivitiFeignApi {

    @Resource
    ActivitiServiceImpl activitiServiceImpl;

    @Resource
    ApproveServiceImpl approveServiceImpl;

    @Override
    @ApiOperation(httpMethod = "POST", value = "提交审批")
    public Wrapper<String> submit(@ApiParam(name = "submit",value="提交审批") @RequestBody ApproSubmitDto approSubmitDto) {
        String processInstanceId=null;
        try {
            String Uid = String.valueOf(approSubmitDto.getUserid());
            String processDefinitionId = approSubmitDto.getProcessDefinitionId();
            Long orderid = approSubmitDto.getOrderId();
            String body = approSubmitDto.getComment();
            String nextUid = String.valueOf(approSubmitDto.getNUserid());
            String taskId = activitiServiceImpl.start(Uid, processDefinitionId);
            activitiServiceImpl.setVariable(taskId, "orderid", orderid);
            activitiServiceImpl.comment(taskId, body);
            Task task=activitiServiceImpl.getTask(taskId);
            processInstanceId=task.getProcessInstanceId();
            approveServiceImpl.complete(taskId, nextUid);
        } catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(processInstanceId);
    }
}
