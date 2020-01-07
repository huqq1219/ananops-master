package com.ananops.provider.service;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * Created by hzq
 * on 2019/12/05
 */
public interface ApproveService {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

/*    //启动流程
    void start(String Uid, String processDefinitionKey,String approveId);*/

    //完成当前任务
    void complete(String taskid, String Uid);

    //通过审批
    void aprrove(String taskId, String Uid);

    //审批不通过
    void disapprove(String taskId);


}
