package com.ananops.provider.service;

/**
 * Created by hzq
 * on 2019/11/25
 */

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;

import java.io.FileNotFoundException;
import java.util.List;

public interface ActivitiService {

    //用activiti自带对象初始化（自动创建数据库表，若已经创建，则直接初始化）
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    //部署流程
    String deploy(String name, String bpmnpath, String pngpath) throws FileNotFoundException;

    //启动流程
    String start(String Uid, String processDefinitionId);

    //根据用户名称查看个人任务
    List<Task> searchByAssi(String Uid);

    //完成个人任务
    void completeByVar(String taskid, String Uid, String variable, Boolean agreement) throws Exception;

    /*    void complete(String taskid, String Uid,String agreement);*/

    //添加批注
    void comment(String taskId, String body);

    //获取批注
    List<Comment> getComment(String TaskId);

    //获取流程定义列表
    List<ProcessDefinition> getDefinitionList();

    //根据部署id获取文件
    String getResource(String deploymentId, String fileName, int version) throws Exception;

    //根据部署id删除流程定义表
    void deleteDefinitionList(String id);

    //级联删除
    void deleteDefAnyWay(String deploymentId);

    //根据执行人查看历史任务节点列表
    List<HistoricTaskInstance> getHistoryList(String Uid);

    //根据历史流程实例id获取历史流程实例对象
    HistoricProcessInstance getProHisIns(String processHistoricInstanceId);

    //根据流程实例id获取流程实例对象
    ProcessInstance getProIns(String processInstanceId);

    //根据流程定义id获取流程定义对象
    ProcessDefinition getProDef(String processDefinitionId);

    //根据taskId获取办理人
    String getAssignee(String taskId);

    //根据任务id获取任务对象
    Task getTask(String taskId);

    //设置、获取流程变量
    void setVariable(String TaskId, String name, Object o);

    Object getVariable(String TaskId, String variableName);

    //获取历史流程变量
    Object getHistoricVariable(String processInstanceId, String variableName);
}
