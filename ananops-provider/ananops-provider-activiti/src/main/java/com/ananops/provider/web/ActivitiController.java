package com.ananops.provider.web;

/**
 * Created by hzq
 * on 2019/11/25
 */

import com.alibaba.fastjson.JSONObject;
import com.ananops.provider.model.dto.ActiDeployDto;
import com.ananops.provider.model.dto.ActiStartDto;
import com.ananops.provider.model.dto.CommentDto;
import com.ananops.provider.model.dto.DeployListDto;
import com.ananops.provider.service.impl.ActivitiServiceImpl;
import com.ananops.provider.utils.WrapMapper;
import com.ananops.provider.utils.Wrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/base",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - Base",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ActivitiController {
    @Autowired
    ActivitiServiceImpl activitiServiceImpl;

    //发布流程
    @PostMapping(value = "/deploy")
    @ApiOperation(httpMethod = "POST",value = "部署流程")
    public Wrapper<String> deploy(@ApiParam(name = "deploy",value="部署流程") @RequestBody ActiDeployDto actiDeployDto) throws Exception {
        String definitionId=null;
        try {
            String name=actiDeployDto.getName();
            String bpmnpath=actiDeployDto.getBpmnpath();
            String pngpath=actiDeployDto.getPngpath();
            definitionId=activitiServiceImpl.deploy(name, bpmnpath, pngpath);
        } catch (Exception e) {
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(definitionId);
    }

    //启动流程
    @PostMapping(value = "/start")
    @ApiOperation(httpMethod = "POST",value = "启动流程")
    public Wrapper<String> start(@ApiParam(name = "start",value="启动流程") @RequestBody ActiStartDto actiStartDto) {
        try {
            activitiServiceImpl.start(String.valueOf(actiStartDto.getUserid()), actiStartDto.getProcessDefinitionId());
        } catch (Exception e) {
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok("success");
    }

/*    @RequestMapping(value = "/approve",method = RequestMethod.POST)
            public String approved(@RequestParam("Uid") String Uid,
                    @RequestParam("taskId") String taskId,
                    @RequestParam(value = "variable",required = false) String variable,
                    @RequestBody String body){
                try {
                    activitiServiceImpl.comment(taskId, body);
                    activitiServiceImpl.aprrove(taskId, Uid, variable);
                }catch (ActivitiObjectNotFoundException e){
                    return "该任务不存在";
        }
        return "审批完成";
    }

    @RequestMapping(value = "/disapprove",method = RequestMethod.POST)
    public String disapprove(@RequestParam("taskId") String taskId,
                             @RequestBody String body){
        try{
            activitiServiceImpl.comment(taskId,body);
            activitiServiceImpl.disapprove(taskId);
        }catch (ActivitiObjectNotFoundException e){
            return "该任务不存在";
        }
        return "审批不通过";
    }*/

/*    @RequestMapping(value = "/complete1",method = RequestMethod.POST)
    public void complete1(@RequestParam("Uid") String Uid,
                            @RequestParam("TaskId") String TaskId,
                            @RequestParam("agreement") String agreement){
      activitiServiceImpl.complete(TaskId,Uid,agreement);
    }*/

/*    //完成任务（推进）
    @RequestMapping(value = "/complete",method = RequestMethod.POST)
    public String complete(@RequestParam("Uid") String Uid,
                           @RequestParam("TaskId") String TaskId,
                           @RequestParam("agreement") Boolean agreement,
                           @RequestParam(value = "variable",required = false,defaultValue = "") String variable,
                           @RequestBody String body){
        try {
            activitiServiceImpl.comment(TaskId, body);
            activitiServiceImpl.completeByVar(TaskId,Uid,variable,agreement);
        }catch (Exception e){
            return "失败！";
        }
         return "成功";
    }*/


    //获取批注
    @GetMapping(value = "/getComment")
    @ApiOperation(httpMethod = "GET",value = "获取批注")
    public Wrapper<List<CommentDto>> comment(@ApiParam(name = "processInstanceId",value = "流程实例id") @RequestParam String processInstanceId) {
        List<CommentDto> res=new ArrayList<>();
        List<Comment> list = activitiServiceImpl.getComment(processInstanceId);
        if (list != null && list.size() > 0) {
            for (Comment comment : list) {
                CommentDto commentDto=new CommentDto();
                String assignee = activitiServiceImpl.getAssignee(comment.getTaskId());
                commentDto.setAssignee(assignee);
                commentDto.setComment(comment.getFullMessage());
                res.add(commentDto);
            }
        }
        return WrapMapper.ok(res);
    }

    //获取部署流程列表
    @GetMapping(value = "/deploylist")
    @ApiOperation(httpMethod = "GET",value = "获取部署流程列表")
    public Wrapper<List<DeployListDto>> personalTask() {
        try {
            List<DeployListDto> res=new ArrayList<>();
            List<ProcessDefinition> list = activitiServiceImpl.getDefinitionList();
            if (list != null && list.size() > 0) {
                for (ProcessDefinition processDefinition : list) {
                    DeployListDto deployListDto=new DeployListDto();
                    deployListDto.setProcessDefinitionId(processDefinition.getId());
                    deployListDto.setProcessDefinitionKey(processDefinition.getKey());
                    deployListDto.setVersion(processDefinition.getVersion());
                    res.add(deployListDto);
                }
            }
            return WrapMapper.ok(res);
        } catch (Exception e) {
            return WrapMapper.error(e.getMessage());
        }
    }

    //获取流程图
    @GetMapping(value = "/image")
    @ApiOperation(httpMethod = "GET",value = "获取流程图")
    public Wrapper<String> getImage(@ApiParam(name = "processDefinitionId",value="流程定义id") @RequestParam String processDefinitionId) throws Exception {
        String path = null;
        try {
            ProcessDefinition processDefinition = activitiServiceImpl.getProDef(processDefinitionId);
            path = activitiServiceImpl.getResource(processDefinition.getDeploymentId()
                    , processDefinition.getKey()
                    , processDefinition.getVersion());
        } catch (Exception e) {
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(path);
    }

    //普通删除流程定义表（根据部署ID）
    @DeleteMapping(value = "/delete")
    @ApiOperation(httpMethod = "DELETE",value = "一般删除流程定义表（根据部署ID）")
    public Wrapper<String> deleteDefinitionList(@ApiParam(name = "processDefinitionId",value="流程定义id") @RequestParam String processDefinitionId) {
        try {
            ProcessDefinition processDefinition = activitiServiceImpl.getProDef(processDefinitionId);
            activitiServiceImpl.deleteDefinitionList(processDefinition.getDeploymentId());
        }  catch (Exception e) {
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok("success");
    }

    //级联删除（强行删除）流程定义表
    @DeleteMapping(value = "/fdelete")
    @ApiOperation(httpMethod = "DELETE",value = "级联删除（强行删除）流程定义表")
    public Wrapper<String> deleteAnyWay(@ApiParam(name = "processDefinitionId",value="流程定义id") @RequestParam String processDefinitionId) {
        try {
            ProcessDefinition processDefinition = activitiServiceImpl.getProDef(processDefinitionId);
            activitiServiceImpl.deleteDefAnyWay(processDefinition.getDeploymentId());
        } catch (Exception e) {
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok("success");
    }
}
