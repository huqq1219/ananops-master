package com.ananops.provider.web;

import com.alibaba.fastjson.JSONObject;
import com.ananops.provider.model.dto.*;
import com.ananops.provider.service.dto.ApproSubmitDto;
import com.ananops.provider.service.impl.ActivitiServiceImpl;
import com.ananops.provider.service.impl.ApproveServiceImpl;
import com.ananops.provider.utils.WrapMapper;
import com.ananops.provider.utils.Wrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzq
 * on 2019/12/05
 */

@RestController
@RequestMapping(value = "/approve",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - Approve",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@CrossOrigin
public class  ApproveController {
    @Autowired
    ActivitiServiceImpl activitiServiceImpl;

    @Autowired
    ApproveServiceImpl approveServiceImpl;

    String variableName = "orderid";
    String startUser = "startUser";

    @PostMapping(value = "/submit")
    @ApiOperation(httpMethod = "POST",value = "提交审批")
    public Wrapper<String> submit(@ApiParam(name = "submit",value="提交审批") @RequestBody ApproSubmitDto approSubmitDto) {
        String Uid=String.valueOf(approSubmitDto.getUserid());
        String processDefinitionId=approSubmitDto.getProcessDefinitionId();
        Long orderid=approSubmitDto.getOrderId();
        String body=approSubmitDto.getComment();
        String nextUid=String.valueOf(approSubmitDto.getNUserid());
        String taskId=activitiServiceImpl.start(Uid,processDefinitionId);
        String processInstanceId=activitiServiceImpl.getTask(taskId).getProcessInstanceId();
        activitiServiceImpl.setVariable(taskId, variableName, orderid);
        activitiServiceImpl.comment(taskId, body);
        approveServiceImpl.complete(taskId, nextUid);
        return WrapMapper.ok(processInstanceId);
    }

    @PostMapping(value = "/agree")
    @ApiOperation(httpMethod = "POST",value = "审批通过")
    public Wrapper<String> agree(@ApiParam(name = "agree",value="审批通过") @RequestBody ApproAgreeDto approAgreeDto) {
        try {
            String taskId=approAgreeDto.getTaskId();
            String Uid=String.valueOf(approAgreeDto.getUserid());
            String body=approAgreeDto.getComment();
            Long orderid = Long.valueOf(activitiServiceImpl.getVariable(taskId, variableName).toString());
            Task task = activitiServiceImpl.getTask(taskId);
            String processInstanceId = task.getProcessInstanceId();
            activitiServiceImpl.comment(taskId, body);
            approveServiceImpl.aprrove(taskId, Uid);
        /*    ProcessInstance processInstance = activitiServiceImpl.getProIns(processInstanceId);
            if (processInstance == null) {
                JSONObject approve = JSON.parseObject(HttpUtil.getApprove(orderid));
                approve.replace("status", approve.get("status"), 1);
                HttpUtil.updateOrderStatus(HttpUtil.stringToRequestBody(approve.toString()));
                //下一步调维修工单服务商分配接口
            }*/
        } catch (Exception e) {
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok("success");
    }

    @PostMapping(value = "/disagree")
    @ApiOperation(httpMethod = "POST",value = "审批不通过")
    public Wrapper<String> disagree(@ApiParam(name = "disagree",value = "审批不通过") @RequestBody ApproDisagreeDto approDisagreeDto) {
        try {
            String body=approDisagreeDto.getComment();
            String taskId=approDisagreeDto.getTaskId();
            Long orderid = Long.valueOf(activitiServiceImpl.getVariable(taskId, variableName).toString());
            activitiServiceImpl.comment(taskId, body);
            approveServiceImpl.disapprove(taskId);
           /* JSONObject approve = JSON.parseObject(HttpUtil.getApprove(orderid));
            approve.replace("status", approve.get("status"), 2);
            HttpUtil.updateOrderStatus(HttpUtil.stringToRequestBody(approve.toString()));*/
        } catch (Exception e) {
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok("success");
    }

    @PostMapping(value = "/untask")
    @ApiOperation(httpMethod = "POST",value = "获取未完成审批列表")
    public Wrapper<PageInfo<UntaskDto>> getUndoTask(@ApiParam(name = "untask",value = "获取未完成审批列表") @RequestBody PageDto pageDto) {
        try {
            List<Task> list = activitiServiceImpl.searchByAssi(String.valueOf(pageDto.getUserid()));
            List<UntaskDto> res = new ArrayList<>();
            PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
            if (list != null && list.size() != 0) {
                for (Task task : list) {
                    if (!activitiServiceImpl.getVariable(task.getId(), variableName).equals(null)) {
                        Object startUid = activitiServiceImpl.getVariable(task.getId(), startUser);
                        Object orderid = activitiServiceImpl.getVariable(task.getId(), variableName);
                        ProcessInstance processInstance = activitiServiceImpl.getProIns(task.getProcessInstanceId());
                        UntaskDto untaskDto = new UntaskDto();
                        untaskDto.setTaskId(task.getId());
                        untaskDto.setStartUser(Long.valueOf(startUid.toString()));
                        untaskDto.setProcessName(processInstance.getProcessDefinitionKey());
                        untaskDto.setProcessDefinitionId(processInstance.getProcessDefinitionId());
                        untaskDto.setProcessInstanceId(processInstance.getId());
                        untaskDto.setTaskName(task.getName());
                        untaskDto.setCreateTime(task.getCreateTime());
                        untaskDto.setOrderId(Long.valueOf(orderid.toString()));
                        res.add(untaskDto);
                    }
                }
            }
            PageInfo<UntaskDto> pageInfo = new PageInfo<>(res);
            return WrapMapper.ok(pageInfo);
        }catch (Exception e) {
            return WrapMapper.error(e.getMessage());
        }
    }

    @PostMapping(value = "/tasked")
    @ApiOperation(httpMethod = "POST",value = "获取已完成审批列表")
    public Wrapper<PageInfo<TaskedDto>> getTasked(@ApiParam(name = "tasked",value = "获取已完成审批列表") @RequestBody PageDto pageDto) {
        try {
            List<HistoricTaskInstance> list = activitiServiceImpl.getHistoryList(String.valueOf(pageDto.getUserid()));
            List<TaskedDto> res = new ArrayList<>();
            PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
            if (list != null && list.size() != 0) {
                for (HistoricTaskInstance task : list) {
                    Object orderid = activitiServiceImpl.getHistoricVariable(task.getProcessInstanceId(), variableName);
                    Object state = activitiServiceImpl.getHistoricVariable(task.getProcessInstanceId(), "state");
                    if (activitiServiceImpl.getProIns(task.getProcessInstanceId()) != null) {
                        state = "审核中";
                    }
                    if (orderid != null && !orderid.equals("") && task.getEndTime() != null) {
                        Object startUid = activitiServiceImpl.getHistoricVariable(task.getProcessInstanceId(), startUser);
                        TaskedDto taskedDto = new TaskedDto();
                        HistoricProcessInstance historicProcessInstance = activitiServiceImpl.getProHisIns(task.getProcessInstanceId());
                        taskedDto.setTaskId(task.getId());
                        taskedDto.setStartUser(Long.valueOf(startUid.toString()));
                        taskedDto.setProcessName(historicProcessInstance.getProcessDefinitionKey());
                        taskedDto.setProcessInstanceId(historicProcessInstance.getId());
                        taskedDto.setProcessDefinitionId(historicProcessInstance.getProcessDefinitionId());
                        taskedDto.setTaskName(task.getName());
                        taskedDto.setCreateTime(task.getCreateTime());
                        taskedDto.setEndTime(task.getEndTime());
                        taskedDto.setState(state.toString());
                        taskedDto.setOrderId(Long.valueOf(orderid.toString()));
                        res.add(taskedDto);
                    }
                }
            }
            PageInfo<TaskedDto> pageInfo = new PageInfo<>(res);
            return WrapMapper.ok(pageInfo);
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
    }

}
