package com.ananops.provider.service.impl;

/**
 * Created by hzq
 * on 2019/11/25
 */

import com.ananops.provider.service.ActivitiService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

@Service
public class ActivitiServiceImpl implements ActivitiService {
    String pngFilePath = "/home/bpmn/pngfile";

    @Override
    public String deploy(String name, String bpmnpath, String pngpath) throws FileNotFoundException {

        File bpmn = new File(bpmnpath);
        File png = new File(pngpath);
        InputStream in1 = new FileInputStream(bpmn);
        InputStream in2 = new FileInputStream(png);

        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment()
                .name(name)
                .addInputStream("bpmn", in1)
                .addInputStream("png", in2)
                .deploy();
        return processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult().toString();
    }

    @Override
    public String start(String Uid, String processDefinitonId) {
        String variableName = "name";                                    //监听器方式设置代办人是在任务创建之后设置，导致历史实例表中没有代办人记录，暂时先采用uel表达式的方式
        String startUser="startUser";
        Map<String, Object> map = new HashMap<>();
        map.put(variableName, Uid);
        map.put(startUser,Uid);
        ProcessInstance processInstance=processEngine.getRuntimeService()
                .startProcessInstanceById(processDefinitonId, map);
        Task task=processEngine.getTaskService()
                .createTaskQuery()
                .processInstanceId(processInstance.getId())
                .singleResult();
        return task.getId();
    }


    @Override
    public void comment(String taskId, String body) {
        Task task = processEngine.getTaskService()
                .createTaskQuery()
                .taskId(taskId)
                .singleResult();
        processEngine.getTaskService().addComment(taskId, task.getProcessInstanceId(), body);
    }


    @Override
    public String getAssignee(String taskId) {
        HistoricTaskInstance task = processEngine.getHistoryService()
                .createHistoricTaskInstanceQuery()
                .taskId(taskId)
                .singleResult();
        return task.getAssignee();

    }

    //获取批注
    @Override
    public List<Comment> getComment(String processInstanceId) {
        List<Comment> comments = new ArrayList<>();
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        List<HistoricActivityInstance> list = processEngine.getHistoryService()
                .createHistoricActivityInstanceQuery()
                .processInstanceId(processInstance.getId())
                .activityType("userTask")
                .list();
        if (list != null && list.size() > 0) {
          /*  Date date=list.get(0).getEndTime();
            String id=list.get(0).getTaskId();
            comments.addAll(processEngine.getTaskService()
                    .getTaskComments(id));*/
            for (HistoricActivityInstance historicActivityInstance : list) {
/*                Date compare = historicActivityInstance.getEndTime();
                if (compare != null) {
                    int com = date.compareTo(compare);
                    if (com > 0) {
                        date = compare;
                        id = historicActivityInstance.getTaskId();
                        comments.addAll(processEngine.getTaskService()
                                .getTaskComments(id));
                    }
                }*/
                comments.addAll(processEngine.getTaskService()
                        .getTaskComments(historicActivityInstance.getTaskId()));
            }
        }
        return comments;
    }

    @Override
    public List<Task> searchByAssi(String Uid) {
        List<Task> list = processEngine.getTaskService()
                .createTaskQuery()
                .taskAssignee(Uid)
                .list();
        return list;
    }

/*    @Override
    public void complete(String taskid, String Uid,String variable){
        String variableName="name";
        Map<String,Object> map=new HashMap<>();
        map.put(variableName,Uid);
        map.put("approveId",variable);
        processEngine.getTaskService().complete(taskid,map);
    }*/

    @Override
    public void completeByVar(String taskid, String Uid, String variable, Boolean derection) throws Exception {
        String agreement;
        Map<String, Object> map = new HashMap<>();
        if (derection == false) {
            agreement = "不同意";
            Task task = processEngine.getTaskService()
                    .createTaskQuery()
                    .taskId(taskid)
                    .singleResult();
            ProcessInstance processInstance = getProIns(task.getProcessInstanceId());
            List<HistoricActivityInstance> list = processEngine.getHistoryService()
                    .createHistoricActivityInstanceQuery()
                    .processInstanceId(processInstance.getId())
                    .activityType("userTask")
                    .list();
            if (list != null && list.size() > 0) {
                HistoricActivityInstance historicActivityInstance1 = list.get(0);
                Date date = list.get(0).getEndTime();
                for (HistoricActivityInstance historicActivityInstance : list) {
                    Date compare = historicActivityInstance.getEndTime();
                    if (compare != null) {
                        int com = date.compareTo(compare);
                        if (com < 0) {
                            date = compare;
                            historicActivityInstance1 = historicActivityInstance;
                        }
                    }
                }
                Uid = historicActivityInstance1.getAssignee();
            } else {
                throw new RuntimeException();
            }
        } else {
            agreement = "同意";
        }

        if (variable == "") {
            map.put("name", Uid);
            map.put("agreement", agreement);
        } else {
            map.put("name", Uid);
            map.put("variable", variable);
            map.put("agreement", agreement);
        }
        TaskService taskService = processEngine.getTaskService();
        taskService.complete(taskid, map);
    }

    @Override
    public List<ProcessDefinition> getDefinitionList() {
        List<ProcessDefinition> list = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .orderByProcessDefinitionName().desc()
                .list();
        return list;
    }

    @Override
    public String getResource(String deploymentId, String fileName, int version) throws Exception {
        List<String> list = processEngine.getRepositoryService()
                .getDeploymentResourceNames(deploymentId);
        String proversion = String.valueOf(version);
        String resourceName = "";
        if (list != null && list.size() > 0) {
            for (String name : list) {
                if (name.indexOf("png") > -1) {
                    resourceName = name;
                }
            }
        } else {
            throw new Exception("该流程不存在！");
        }

        InputStream inputStream = processEngine.getRepositoryService()
                .getResourceAsStream(deploymentId, resourceName);
        File file = new File(pngFilePath + fileName + proversion + "." + resourceName);
        if (!file.exists()) {
            FileUtils.copyInputStreamToFile(inputStream, file);
        }
        return pngFilePath + fileName + proversion + "." + resourceName;
    }

    @Override
    public void deleteDefinitionList(String deploymentId) {
        processEngine.getRepositoryService()
                .deleteDeployment(deploymentId);
    }

    @Override
    public void deleteDefAnyWay(String deploymentId) {
        processEngine.getRepositoryService()
                .deleteDeployment(deploymentId, true);
    }


    @Override
    public List<HistoricTaskInstance> getHistoryList(String Uid) {
        return processEngine.getHistoryService()
                .createHistoricTaskInstanceQuery()
                .taskAssignee(Uid)
                .list();
    }


    @Override
    public ProcessInstance getProIns(String processInstanceId) {
        return processEngine.getRuntimeService()
                .createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
    }


    @Override
    public HistoricProcessInstance getProHisIns(String processInstanceId) {
        return processEngine.getHistoryService()
                .createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();

    }

    @Override
    public ProcessDefinition getProDef(String processDefinitionId) {
        ProcessDefinition processDefinition = null;
        try {
            processDefinition = processEngine.getRepositoryService()
                    .createProcessDefinitionQuery()
                    .processDefinitionId(processDefinitionId)
                    .singleResult();
        } catch (NullPointerException e) {
            throw e;
        }
        return processDefinition;
    }

    @Override
    public Task getTask(String taskId) {
        return processEngine.getTaskService()
                .createTaskQuery()
                .taskId(taskId)
                .singleResult();
    }

    @Override
    public void setVariable(String taskId, String variableName, Object o) {
        processEngine.getTaskService()
                .setVariable(taskId, variableName, o);
    }


    @Override
    public Object getVariable(String taskId, String variableName) {
        return processEngine.getTaskService()
                .getVariable(taskId, variableName);
    }

    @Override
    public Object getHistoricVariable(String processInstanceId, String variableName) {
        HistoricVariableInstance historicVariableInstance = processEngine.getHistoryService()
                .createHistoricVariableInstanceQuery()
                .processInstanceId(processInstanceId)
                .variableName(variableName)
                .singleResult();
         if(historicVariableInstance==null){
            return null;
        }
        return historicVariableInstance.getValue();
    }

}
