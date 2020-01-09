package com.ananops.provider.service.impl;


import com.ananops.base.dto.LoginAuthDto;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.base.exception.BusinessException;
import com.ananops.core.support.BaseService;

import com.ananops.provider.mapper.*;
import com.ananops.provider.model.domain.*;
import com.ananops.provider.model.dto.*;
import com.ananops.provider.model.enums.*;
import com.ananops.provider.service.MdmcTaskItemService;
import com.ananops.provider.service.MdmcTaskService;
import com.ananops.wrapper.Wrapper;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service
public class MdmcTaskServiceImpl extends BaseService<MdmcTask> implements MdmcTaskService {
    @Resource
    MdmcTaskMapper taskMapper;

    @Resource
    MdmcTaskLogMapper taskLogMapper;

    @Resource
    MdmcTaskItemService taskItemService;


    @Override
    public MdmcTask getTaskByTaskId(Long taskId) {
        return taskMapper.selectByPrimaryKey(taskId);
    }

    @Override
    public MdmcAddTaskDto saveTask(MdmcAddTaskDto mdmcAddTaskDto,LoginAuthDto loginAuthDto) {

        MdmcTask task = new MdmcTask();
        copyProperties(mdmcAddTaskDto,task);
        task.setUpdateInfo(loginAuthDto);
        if (mdmcAddTaskDto.getUserId()==null){
           throw new BusinessException(ErrorCodeEnum.GL99990003);
        }
//        MqMessageData mqMessageData;
//        String body = JSON.toJSONString(mdmcAddTaskDto);
//        String topic = AliyunMqTopicConstants.MqTagEnum.UPDATE_INSPECTION_TASK.getTopic();
//        String tag = AliyunMqTopicConstants.MqTagEnum.UPDATE_INSPECTION_TASK.getTag();
        if(mdmcAddTaskDto.getId()==null){
            logger.info("创建一条维修工单记录... CrateTaskInfo = {}", mdmcAddTaskDto);

            //如果当前是新建一条任务
            //获取所有的巡检任务子项
            List<MdmcAddTaskItemDto> mdmcAddTaskItemDtoList = mdmcAddTaskDto.getMdmcAddTaskItemDtoList();
            Long taskId = super.generateId();
            task.setId(taskId);
//            String key = RedisKeyUtil.createMqKey(topic,tag,String.valueOf(taskId),body);
//            mqMessageData = new MqMessageData(body, topic, tag, key);
//            taskManager.saveTask(mqMessageData,task,true);
            task.setStatus(2);
            taskMapper.insert(task);
            logger.info("新创建一条维修记录：" + task.toString());
            mdmcAddTaskItemDtoList.forEach(taskItem->{//保存所有任务子项
                taskItem.setTaskId(taskId);//设置任务子项对应的任务id
                //创建新的任务子项，并更新返回结果
                copyProperties(taskItemService.saveItem(taskItem,loginAuthDto),taskItem);
            });
            //更新返回结果

            copyProperties(task,mdmcAddTaskDto);
            copyProperties(mdmcAddTaskItemDtoList,mdmcAddTaskDto);

            logger.info("创建维修工单成功[OK] Task = {}", task);

        }else{
            logger.info("编辑/修改维修工单详情... UpdateInfo = {}", mdmcAddTaskDto);

            Long taskId = mdmcAddTaskDto.getId();
            Example example = new Example(MdmcTask.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("id",taskId);
            List<MdmcTask> taskList =taskMapper.selectByExample(example);
            if(taskList.size()==0){//如果没有此任务
                throw new BusinessException(ErrorCodeEnum.GL9999098,taskId);
            }
            //如果当前是更新一条记录
//            String key = RedisKeyUtil.createMqKey(topic,tag,String.valueOf(task.getId()),body);
//            mqMessageData = new MqMessageData(body, topic, tag, key);
//            taskManager.saveTask(mqMessageData,task,false);
            taskMapper.updateByPrimaryKeySelective(task);
            //更新返回结果
            copyProperties(task,mdmcAddTaskDto);

            logger.info("编辑/修改维修工单成功[OK] Task = {}", task);

        }

        System.out.println(task.getTitle());
        return mdmcAddTaskDto;
    }

    @Override
    public MdmcTaskDto modifyTask(MdmcTaskDto mdmcTaskDto) {
        logger.info("修改维修工单详情... UpdateInfo = {}", mdmcTaskDto);

        MdmcTask task = new MdmcTask();
        copyProperties(mdmcTaskDto,task);
        Long taskId = mdmcTaskDto.getId();
        if (taskId==null){
            throw new BusinessException(ErrorCodeEnum.GL9999098,taskId);
        }
        Example example = new Example(MdmcTask.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",taskId);
        List<MdmcTask> taskList =taskMapper.selectByExample(example);
        if(taskList.size()==0){//如果没有此任务
            throw new BusinessException(ErrorCodeEnum.GL9999098,taskId);
        }
        //如果当前是更新一条记录
//            String key = RedisKeyUtil.createMqKey(topic,tag,String.valueOf(task.getId()),body);
//            mqMessageData = new MqMessageData(body, topic, tag, key);
//            taskManager.saveTask(mqMessageData,task,false);
        taskMapper.updateByPrimaryKeySelective(task);
        //更新返回结果
        copyProperties(task,mdmcTaskDto);

        logger.info("修改维修工单详情成功[OK] Task = {}", task);
        return mdmcTaskDto;
    }


    @Override
    public MdmcTask modifyTaskStatus(MdmcChangeStatusDto changeStatusDto,LoginAuthDto loginAuthDto) {
        logger.info("更新维修工单状态... UpdateInfo = {}", changeStatusDto);

//        MqMessageData mqMessageData;
        Long taskId = changeStatusDto.getTaskId();
        Integer status = changeStatusDto.getStatus();
        Example example = new Example(MdmcTask.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",taskId);
        if(taskMapper.selectCountByExample(example)==0){//如果当前任务不存在
            throw new BusinessException(ErrorCodeEnum.GL9999098,taskId);
        }
        //如果当前任务存在
        MdmcTask task = taskMapper.selectByExample(example).get(0);//获取当前任务
        changeStatusDto.setStatusMsg(MdmcTaskStatusEnum.getStatusMsg(status));
        if (status==1){taskMapper.deleteByPrimaryKey(taskId);}
        else if (status==14){FacilitatorTransfer();}
        else if (status==15){MaintainerTransfer();}
        task.setId(taskId);
        task.setStatus(status);
        task.setUpdateInfo(loginAuthDto);
//        String body = JSON.toJSONString(changeStatusDto);
//        String topic = AliyunMqTopicConstants.MqTagEnum.MODIFY_INSPECTION_TASK_STATUS.getTopic();
//        String tag = AliyunMqTopicConstants.MqTagEnum.MODIFY_INSPECTION_TASK_STATUS.getTag();
//        String key = RedisKeyUtil.createMqKey(topic,tag,String.valueOf(task.getId()),body);
//        mqMessageData = new MqMessageData(body, topic, tag, key);
//        taskManager.modifyTaskStatus(mqMessageData,task);
        taskMapper.updateByPrimaryKey(task);
        logger.info("更新维修工单状态成功[OK] Task = {}", task);

        MdmcTaskLog taskLog=new MdmcTaskLog();
        Long taskLogId = super.generateId();
        taskLog.setId(taskLogId);
        taskLog.setTaskId(taskId);
        taskLog.setStatus(status);
        taskLog.setMovement(MdmcTaskStatusEnum.getStatusMsg(status));
        taskLogMapper.insert(taskLog);

        logger.info("记录维修工单操作日志[OK] TaskLog = {}", taskLog);

        return task;
    }

    @Override
    public Void FacilitatorTransfer() {
        return null;
    }

    @Override
    public Void MaintainerTransfer() {
        return null;
    }

    @Override
    public List<MdmcTask> getTaskListByStatus(MdmcStatusDto statusDto) {
        Example example = new Example(MdmcTask.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status",statusDto.getStatus());
        if(taskMapper.selectCountByExample(example)==0){
            throw new BusinessException(ErrorCodeEnum.GL9999098);
        }
        PageHelper.startPage(statusDto.getPageNum(),statusDto.getPageSize());
        return taskMapper.selectByExample(example);
    }

    @Override
    public List<MdmcTask> getTaskList(MdmcStatusDto statusDto) {

        PageHelper.startPage(statusDto.getPageNum(),statusDto.getPageSize());
        return taskMapper.selectAll();
    }

    @Override
    public List<MdmcTask> getTaskListByIdAndStatus(MdmcQueryDto queryDto) {
        String roleCode=queryDto.getRoleCode();
        Long id=queryDto.getId();
        Integer status=queryDto.getStatus();
        Example example = new Example(MdmcTask.class);
        Example.Criteria criteria = example.createCriteria();
        if (status!=null){
        criteria.andEqualTo("status",queryDto.getStatus());
        }
        switch (roleCode){
            case "user_watcher":criteria.andEqualTo("userId",id);break;
            case "user_manager":criteria.andEqualTo("principalId",id);break;
            case "engineer":criteria.andEqualTo("maintainerId",id);break;
            case "fac_service":criteria.andEqualTo("facilitatorId",id);break;
            default: throw new BusinessException(ErrorCodeEnum.UAC10012008,roleCode);
        }
        if(taskMapper.selectCountByExample(example)==0){
            throw new BusinessException(ErrorCodeEnum.GL9999098);
        }
        PageHelper.startPage(queryDto.getPageNum(),queryDto.getPageSize());
        return taskMapper.selectByExample(example);

    }

    @Override
    public List<MdmcListDto> getTaskListByIdAndStatusArrary(MdmcStatusArrayDto statusArrayDto) {
        String roleCode=statusArrayDto.getRoleCode();
        Long id=statusArrayDto.getId();
        Integer[] status=statusArrayDto.getStatus();
        Example example = new Example(MdmcTask.class);
        Example.Criteria criteria = example.createCriteria();
        List<MdmcListDto> listDtoList=new ArrayList<>();
        switch (roleCode){
            case "user_watcher":criteria.andEqualTo("userId",id);break;
            case "user_manager":criteria.andEqualTo("principalId",id);break;
            case "engineer":criteria.andEqualTo("maintainerId",id);break;
            case "fac_service":criteria.andEqualTo("facilitatorId",id);break;
            default: throw new BusinessException(ErrorCodeEnum.UAC10012008,roleCode);
        }
        if (status!=null){
            for (Integer i:status){
                Example example1 = new Example(MdmcTask.class);
                Example.Criteria criteria1 = example1.createCriteria();
                criteria1.andEqualTo("status",i);
                switch (roleCode){
                    case "user_watcher":criteria1.andEqualTo("userId",id);break;
                    case "user_manager":criteria1.andEqualTo("principalId",id);break;
                    case "engineer":criteria1.andEqualTo("maintainerId",id);break;
                    case "fac_service":criteria1.andEqualTo("facilitatorId",id);break;
                    default: throw new BusinessException(ErrorCodeEnum.UAC10012008,roleCode);
                }
                MdmcListDto listDto=new MdmcListDto();
                listDto.setStatus(i);
                listDto.setTaskList(taskMapper.selectByExample(example1));
                listDtoList.add(listDto);
            }
        }
       else{
        MdmcListDto listDto1=new MdmcListDto();

        listDto1.setTaskList(taskMapper.selectByExample(example));
        listDtoList.add(listDto1);}
        PageHelper.startPage(statusArrayDto.getPageNum(),statusArrayDto.getPageSize());
        return listDtoList;
    }

    @Override
    public MdmcPageDto getTaskListByPage(MdmcQueryDto queryDto) {
        String roleCode=queryDto.getRoleCode();
        Long id=queryDto.getId();
        Integer status=queryDto.getStatus();
        Example example = new Example(MdmcTask.class);
        Example.Criteria criteria = example.createCriteria();
        if (status!=null){
            criteria.andEqualTo("status",queryDto.getStatus());
        }
        switch (roleCode){
            case "user_watcher":criteria.andEqualTo("userId",id);break;
            case "user_manager":criteria.andEqualTo("principalId",id);break;
            case "engineer":criteria.andEqualTo("maintainerId",id);break;
            case "fac_service":criteria.andEqualTo("facilitatorId",id);break;
            default: throw new BusinessException(ErrorCodeEnum.UAC10012008,roleCode);
        }
        if(taskMapper.selectCountByExample(example)==0){
            throw new BusinessException(ErrorCodeEnum.GL9999098);
        }
        PageHelper.startPage(queryDto.getPageNum(),queryDto.getPageSize());
        MdmcPageDto pageDto=new MdmcPageDto();
        pageDto.setTaskList(taskMapper.selectByExample(example));
        pageDto.setPageNum(queryDto.getPageNum());
        pageDto.setPageSize(queryDto.getPageSize());

        return pageDto;

    }

    @Override
    public MdmcTask modifyMaintainer(MdmcChangeMaintainerDto mdmcChangeMaintainerDto){
        LoginAuthDto loginAuthDto = mdmcChangeMaintainerDto.getLoginAuthDto();
        Long taskId = mdmcChangeMaintainerDto.getTaskId();
        Long maintainerId = mdmcChangeMaintainerDto.getMaintainerId();
        Example example = new Example(MdmcTask.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",taskId);
        if(taskMapper.selectCountByExample(example) == 0){//如果当前任务不存在
            throw new BusinessException(ErrorCodeEnum.GL9999098);
        }
        MdmcTask mdmcTask = taskMapper.selectByExample(example).get(0);
        mdmcTask.setMaintainerId(maintainerId);
        mdmcTask.setUpdateInfo(loginAuthDto);
        taskMapper.updateByPrimaryKey(mdmcTask);
        return mdmcTask;
    }

    /**
     * 工程师拒单
     * @param refuseMdmcTaskDto
     * @return
     */
    @Override
    public MdmcChangeStatusDto refuseTaskByMaintainer(RefuseMdmcTaskDto refuseMdmcTaskDto){
        Long taskId = refuseMdmcTaskDto.getTaskId();
        LoginAuthDto loginAuthDto = refuseMdmcTaskDto.getLoginAuthDto();
        MdmcChangeStatusDto mdmcChangeStatusDto = new MdmcChangeStatusDto();
        Example example = new Example(MdmcTask.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",taskId);
        if(taskMapper.selectCountByExample(example) == 0){//如果当前任务不存在
            throw new BusinessException(ErrorCodeEnum.GL9999098);
        }
        MdmcTask mdmcTask = taskMapper.selectByExample(example).get(0);
        if(mdmcTask.getStatus()==MdmcTaskStatusEnum.JieDan3.getStatusNum()){
            //如果当前任务的状态处于“已分配维修工，待维修工接单”，工程师才能拒单
            mdmcChangeStatusDto.setLoginAuthDto(loginAuthDto);
            mdmcChangeStatusDto.setStatus(MdmcTaskStatusEnum.JieDan2.getStatusNum());
            mdmcChangeStatusDto.setStatusMsg(MdmcTaskStatusEnum.JieDan2.getStatusMsg());
            this.modifyTaskStatus(mdmcChangeStatusDto,loginAuthDto);

        }else{
            throw new BusinessException(ErrorCodeEnum.GL9999087);
        }
        return mdmcChangeStatusDto;
    }

    /**
     * 服务商拒单
     * @param refuseMdmcTaskDto
     * @return
     */
    @Override
    public MdmcChangeStatusDto refuseTaskByFacilitator(RefuseMdmcTaskDto refuseMdmcTaskDto){
        Long taskId = refuseMdmcTaskDto.getTaskId();
        LoginAuthDto loginAuthDto = refuseMdmcTaskDto.getLoginAuthDto();
        MdmcChangeStatusDto mdmcChangeStatusDto = new MdmcChangeStatusDto();
        Example example = new Example(MdmcTask.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",taskId);
        if(taskMapper.selectCountByExample(example) == 0){//如果当前任务不存在
            throw new BusinessException(ErrorCodeEnum.GL9999098);
        }
        MdmcTask mdmcTask = taskMapper.selectByExample(example).get(0);
        if(mdmcTask.getStatus()==MdmcTaskStatusEnum.JieDan1.getStatusNum()){
            //如果当前任务的状态处于“审核通过，待服务商接单”，服务商才能拒单
            mdmcChangeStatusDto.setLoginAuthDto(loginAuthDto);
            mdmcChangeStatusDto.setStatus(MdmcTaskStatusEnum.ShenHeZhong1.getStatusNum());
            mdmcChangeStatusDto.setStatusMsg(MdmcTaskStatusEnum.ShenHeZhong1.getStatusMsg());
            this.modifyTaskStatus(mdmcChangeStatusDto,loginAuthDto);

        }else{
            throw new BusinessException(ErrorCodeEnum.GL9999087);
        }
        return mdmcChangeStatusDto;
    }


    public String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public void copyProperties(Object source, Object target){
        String[] ignore = getNullPropertyNames(source);
        BeanUtils.copyProperties(source, target, ignore);
    }

}
