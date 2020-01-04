package com.ananops.provider.service.impl;


import com.alibaba.fastjson.JSON;
import com.ananops.RedisKeyUtil;
import com.ananops.base.constant.AliyunMqTopicConstants;
import com.ananops.base.dto.LoginAuthDto;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.base.exception.BusinessException;
import com.ananops.core.support.BaseService;
import com.ananops.provider.manager.ImcTaskManager;
import com.ananops.provider.mapper.*;
import com.ananops.provider.model.domain.*;
import com.ananops.provider.model.dto.*;
import com.ananops.provider.model.enums.ItemStatusEnum;
import com.ananops.provider.model.enums.TaskStatusEnum;
import com.ananops.provider.service.ImcInspectionItemService;
import com.ananops.provider.service.ImcInspectionTaskService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rongshuai on 2019/11/27 19:31
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ImcInspectionTaskServiceImpl extends BaseService<ImcInspectionTask> implements ImcInspectionTaskService {
    @Resource
    ImcInspectionTaskMapper imcInspectionTaskMapper;

    @Resource
    ImcTaskManager imcTaskManager;

    @Resource
    ImcInspectionItemService imcInspectionItemService;

    @Resource
    ImcUserTaskMapper imcUserTaskMapper;

    @Resource
    ImcInspectionItemMapper imcInspectionItemMapper;

    @Resource
    ImcFacilitatorManagerTaskMapper imcFacilitatorManagerTaskMapper;

    @Resource
    ImcFacilitatorGroupTaskMapper imcFacilitatorGroupTaskMapper;



    /**
     * 插入一条巡检任务记录
     * @param imcAddInspectionTaskDto
     * @return
     */
    public ImcAddInspectionTaskDto saveTask(ImcAddInspectionTaskDto imcAddInspectionTaskDto, LoginAuthDto loginAuthDto){
        ImcInspectionTask imcInspectionTask = new ImcInspectionTask();
        BeanUtils.copyProperties(imcAddInspectionTaskDto,imcInspectionTask);
        imcInspectionTask.setUpdateInfo(loginAuthDto);
        MqMessageData mqMessageData;
        String body = JSON.toJSONString(imcAddInspectionTaskDto);
        String topic = AliyunMqTopicConstants.MqTagEnum.UPDATE_INSPECTION_TASK.getTopic();
        String tag = AliyunMqTopicConstants.MqTagEnum.UPDATE_INSPECTION_TASK.getTag();
        if(imcInspectionTask.isNew()){
            //如果当前是新建一条任务
            //获取所有的巡检任务子项
            List<ImcAddInspectionItemDto> imcAddInspectionItemDtoList = imcAddInspectionTaskDto.getImcAddInspectionItemDtoList();
            Long taskId = super.generateId();
            Long userId = imcAddInspectionTaskDto.getUserId();
            Long facilitatorManagerId = imcAddInspectionTaskDto.getFacilitatorManagerId();
            Long facilitatorGroupId = imcAddInspectionTaskDto.getFacilitatorGroupId();
            imcInspectionTask.setId(taskId);
            //将巡检任务状态设置为等待服务商接单
            imcInspectionTask.setStatus(TaskStatusEnum.WAITING_FOR_FACILITATOR.getStatusNum());
            String key = RedisKeyUtil.createMqKey(topic,tag,String.valueOf(taskId),body);
            mqMessageData = new MqMessageData(body, topic, tag, key);
            imcTaskManager.saveInspectionTask(mqMessageData,imcInspectionTask,true);
            logger.info("新创建一条巡检记录：" + imcInspectionTask.toString());
            //增加一条甲方用户和巡检任务的关系记录
            ImcUserTask imcUserTask = new ImcUserTask();
            imcUserTask.setTaskId(taskId);
            imcUserTask.setUserId(userId);
            imcUserTaskMapper.insert(imcUserTask);
            //增加一条服务商管理员和巡检任务的关系记录
            ImcFacilitatorManagerTask imcFacilitatorManagerTask = new ImcFacilitatorManagerTask();
            imcFacilitatorManagerTask.setFacilitatorManagerId(facilitatorManagerId);
            imcFacilitatorManagerTask.setTaskId(taskId);
            imcFacilitatorManagerTaskMapper.insert(imcFacilitatorManagerTask);
            //增加一条服务商组织和巡检任务的关系记录
            ImcFacilitatorGroupTask imcFacilitatorGroupTask = new ImcFacilitatorGroupTask();
            imcFacilitatorGroupTask.setFacilitatorGroupId(facilitatorGroupId);
            imcFacilitatorGroupTask.setTaskId(taskId);
            imcFacilitatorGroupTaskMapper.insert(imcFacilitatorGroupTask);
            //保存新创建的巡检任务子项
            imcAddInspectionItemDtoList.forEach(item->{//保存所有巡检任务子项
                item.setInspectionTaskId(taskId);//设置巡检任务子项对应的任务id
                item.setDays(imcInspectionTask.getDays());//设置巡检任务子项对应的巡检周期
                item.setFrequency(imcInspectionTask.getFrequency());//设置巡检任务子项对应的巡检频率
                item.setScheduledStartTime(imcInspectionTask.getScheduledStartTime());//设置巡检任务子项的对应的计划开始时间
                Long scheduledStartTime = item.getScheduledStartTime().getTime();//获得巡检任务子项的预计开始时间
                Long currentTime = System.currentTimeMillis();//获得当前时间
                if(scheduledStartTime<=currentTime){//如果计划执行时间<=当前时间，说明，巡检任务需要立即执行
                    //将巡检任务子项的状态设置为等待巡检
                    item.setStatus(ItemStatusEnum.WAITING_FOR_INSPECTION.getStatusNum());
                }
                //创建新的任务子项，并更新返回结果
                BeanUtils.copyProperties(imcInspectionItemService.saveInspectionItem(item,loginAuthDto),item);
            });
            //更新返回结果
            BeanUtils.copyProperties(imcInspectionTask,imcAddInspectionTaskDto);
            BeanUtils.copyProperties(imcAddInspectionItemDtoList,imcAddInspectionTaskDto);
        }else{
            //如果当前是更新一条记录
            String key = RedisKeyUtil.createMqKey(topic,tag,String.valueOf(imcInspectionTask.getId()),body);
            mqMessageData = new MqMessageData(body, topic, tag, key);
            imcTaskManager.saveInspectionTask(mqMessageData,imcInspectionTask,false);
            //更新返回结果
            BeanUtils.copyProperties(imcInspectionTask,imcAddInspectionTaskDto);
        }
        System.out.println(imcInspectionTask.getTaskName());
        return imcAddInspectionTaskDto;
    }

    /**
     * 根据巡检任务的Id获取巡检任务
     * @param taskId
     * @return
     */
    public ImcInspectionTask getTaskByTaskId(Long taskId){//根据巡检任务的ID，获取巡检任务的详情
        return imcInspectionTaskMapper.selectByPrimaryKey(taskId);
    }


    /**
     * 修改巡检任务的状态
     * @param imcTaskChangeStatusDto
     * @param loginAuthDto
     * @return
     */
    public ImcInspectionTask modifyTaskStatus(ImcTaskChangeStatusDto imcTaskChangeStatusDto, LoginAuthDto loginAuthDto){
        MqMessageData mqMessageData;
        Long taskId = imcTaskChangeStatusDto.getTaskId();
        Integer status = imcTaskChangeStatusDto.getStatus();
        ImcInspectionTask imcInspectionTask = new ImcInspectionTask();
        Example example = new Example(ImcInspectionTask.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",taskId);
        if(imcInspectionTaskMapper.selectCountByExample(example)==0){//如果当前任务不存在
            throw new BusinessException(ErrorCodeEnum.GL9999098,taskId);
        }
        //如果当前任务存在
        imcTaskChangeStatusDto.setStatusMsg(TaskStatusEnum.getStatusMsg(status));
        imcInspectionTask.setId(taskId);
        imcInspectionTask.setStatus(status);
        imcInspectionTask.setUpdateInfo(loginAuthDto);
        String body = JSON.toJSONString(imcTaskChangeStatusDto);
        String topic = AliyunMqTopicConstants.MqTagEnum.MODIFY_INSPECTION_TASK_STATUS.getTopic();
        String tag = AliyunMqTopicConstants.MqTagEnum.MODIFY_INSPECTION_TASK_STATUS.getTag();
        String key = RedisKeyUtil.createMqKey(topic,tag,String.valueOf(imcInspectionTask.getId()),body);
        mqMessageData = new MqMessageData(body, topic, tag, key);
        imcTaskManager.modifyTaskStatus(mqMessageData,imcInspectionTask);
        return imcInspectionTask;
    }

    /**
     * 获得某一状态下的全部巡检任务
     * @param taskQueryDto
     * @return
     */
    public List<ImcInspectionTask> getTaskByStatus(TaskQueryDto taskQueryDto){
        Example example = new Example(ImcInspectionTask.class);
        Example.Criteria criteria = example.createCriteria();
        Integer status = taskQueryDto.getStatus();
        String taskName = taskQueryDto.getTaskName();
        if(StringUtils.isNotBlank(taskName)){
            taskName = "%" + taskName + "%";
            criteria.andLike("taskName",taskName);
        }
        criteria.andEqualTo("status",status);
        if(imcInspectionTaskMapper.selectCountByExample(example)==0){//当前状态没有对应的任务
            throw new BusinessException(ErrorCodeEnum.GL9999091);
        }
        PageHelper.startPage(taskQueryDto.getPageNum(),taskQueryDto.getPageSize());
        return imcInspectionTaskMapper.selectByExample(example);
    }

    /**
     * 修改巡检任务的名字
     * @param taskNameChangeDto
     * @param loginAuthDto
     * @return
     */
    public ImcInspectionTask modifyTaskName(TaskNameChangeDto taskNameChangeDto,LoginAuthDto loginAuthDto){
        ImcInspectionTask imcInspectionTask = new ImcInspectionTask();
        Example example = new Example(ImcInspectionTask.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",taskNameChangeDto.getTaskId());
        if(imcInspectionTaskMapper.selectCountByExample(example)==0){
            throw new BusinessException(ErrorCodeEnum.GL9999098);
        }
        imcInspectionTask = imcInspectionTaskMapper.selectByExample(example).get(0);
        imcInspectionTask.setTaskName(taskNameChangeDto.getTaskName());
        imcInspectionTask.setUpdateInfo(loginAuthDto);
        imcInspectionTaskMapper.updateByPrimaryKeySelective(imcInspectionTask);
        return imcInspectionTask;
    }

    /**
     * 根据项目id查询对应的巡检任务
     * @param taskQueryDto
     * @return
     */
    public List<ImcInspectionTask> getTaskByProjectId(TaskQueryDto taskQueryDto){
        Example example = new Example(ImcInspectionTask.class);
        Example.Criteria criteria = example.createCriteria();
        Long projectId = taskQueryDto.getProjectId();
        String taskName = taskQueryDto.getTaskName();
        if(StringUtils.isNotBlank(taskName)){
            taskName = "%" + taskName + "%";
            criteria.andLike("taskName",taskName);
        }
        criteria.andEqualTo("projectId",projectId);
        if(imcInspectionTaskMapper.selectCountByExample(example)==0){//当前状态没有对应的任务
            throw new BusinessException(ErrorCodeEnum.GL9999091);
        }
        PageHelper.startPage(taskQueryDto.getPageNum(),taskQueryDto.getPageSize());
        return imcInspectionTaskMapper.selectByExample(example);
    }

    /**
     * 根据用户id获取对应的巡检任务
     * @param taskQueryDto
     * @return
     */
    public List<ImcInspectionTask> getTaskByUserId(TaskQueryDto taskQueryDto){
        Integer role = taskQueryDto.getRole();
        PageHelper.startPage(taskQueryDto.getPageNum(),taskQueryDto.getPageSize());
        String taskName = taskQueryDto.getTaskName();
        if(StringUtils.isNotBlank(taskName)){
            taskName = "%" + taskName + "%";
            switch (role){
                case 1://如果角色是甲方用户
                    return imcInspectionTaskMapper.queryTaskByUserIdAndTaskName(taskQueryDto.getUserId(),taskName);
                case 2://如果角色是服务商
                    return this.getTaskByFacilitatorId(taskQueryDto);
                case 3://如果角色是服务商管理员
                    return imcInspectionTaskMapper.queryTaskByFacilitatorManagerIdAndTaskName(taskQueryDto.getUserId(),taskName);
                case 4://如果角色是服务商组织
                    return imcInspectionTaskMapper.queryTaskByFacilitatorGroupIdAndTaskName(taskQueryDto.getUserId(),taskName);
                default:
                    throw new BusinessException(ErrorCodeEnum.GL9999089);
            }
        }else{
            switch (role){
                case 1://如果角色是甲方用户
                    return imcInspectionTaskMapper.queryTaskByUserId(taskQueryDto.getUserId());
                case 2://如果角色是服务商
                    return this.getTaskByFacilitatorId(taskQueryDto);
                case 3://如果角色是服务商管理员
                    return imcInspectionTaskMapper.queryTaskByFacilitatorManagerId(taskQueryDto.getUserId());
                case 4://如果角色是服务商组织
                    return imcInspectionTaskMapper.queryTaskByFacilitatorGroupId(taskQueryDto.getUserId());
                default:
                    throw new BusinessException(ErrorCodeEnum.GL9999089);
            }
        }
    }

    /**
     * 根据用户Id查询对应状态的巡检任务
     * @param taskQueryDto
     * @return
     */
    public List<ImcInspectionTask> getTaskByUserIdAndStatus(TaskQueryDto taskQueryDto){
        Integer role = taskQueryDto.getRole();
        PageHelper.startPage(taskQueryDto.getPageNum(),taskQueryDto.getPageSize());
        String taskName = taskQueryDto.getTaskName();
        if(StringUtils.isNotBlank(taskName)){
            taskName = "%" + taskName + "%";
            switch (role){
                case 1://如果角色是甲方用户
                    return imcInspectionTaskMapper.queryTaskByUserIdAndStatusAndTaskName(taskQueryDto.getUserId(),taskQueryDto.getStatus(),taskName);
                case 2://如果角色是服务商
                    return this.getTaskByFacilitatorIdAndStatus(taskQueryDto);
                case 3://如果角色是服务商管理员
                    return imcInspectionTaskMapper.queryTaskByFacilitatorManagerIdAndStatusAndTaskName(taskQueryDto.getUserId(),taskQueryDto.getStatus(),taskName);
                case 4://如果角色是服务商组织
                    return imcInspectionTaskMapper.queryTaskByFacilitatorGroupIdAndStatusAndTaskName(taskQueryDto.getUserId(),taskQueryDto.getStatus(),taskName);
                default:
                    throw new BusinessException(ErrorCodeEnum.GL9999089);
            }
        }else{
            switch (role){
                case 1://如果角色是甲方用户
                    return imcInspectionTaskMapper.queryTaskByUserIdAndStatus(taskQueryDto.getUserId(),taskQueryDto.getStatus());
                case 2://如果角色是服务商
                    return this.getTaskByFacilitatorIdAndStatus(taskQueryDto);
                case 3://如果角色是服务商管理员
                    return imcInspectionTaskMapper.queryTaskByFacilitatorManagerIdAndStatus(taskQueryDto.getUserId(),taskQueryDto.getStatus());
                case 4://如果角色是服务商组织
                    return imcInspectionTaskMapper.queryTaskByFacilitatorGroupIdAndStatus(taskQueryDto.getUserId(),taskQueryDto.getStatus());
                default:
                    throw new BusinessException(ErrorCodeEnum.GL9999089);
            }
        }
    }

    /**
     * 根据服务商Id查询服务商对应的巡检任务
     * @param taskQueryDto
     * @return
     */
    public List<ImcInspectionTask> getTaskByFacilitatorId(TaskQueryDto taskQueryDto){
        Long facilitatorId = taskQueryDto.getUserId();
        Example example = new Example(ImcInspectionTask.class);
        Example.Criteria criteria = example.createCriteria();
        String taskName = taskQueryDto.getTaskName();
        if(StringUtils.isNotBlank(taskName)){
            taskName = "%" + taskName + "%";
            criteria.andLike("taskName",taskName);
        }
        criteria.andEqualTo("facilitatorId",facilitatorId);
        return imcInspectionTaskMapper.selectByExample(example);
    }

    /**
     * 根据服务商id查询对应状态的巡检任务
     * @param taskQueryDto
     * @return
     */
    public List<ImcInspectionTask> getTaskByFacilitatorIdAndStatus(TaskQueryDto taskQueryDto){
        Long facilitatorId = taskQueryDto.getUserId();
        Integer status = taskQueryDto.getStatus();
        Example example = new Example(ImcInspectionTask.class);
        Example.Criteria criteria = example.createCriteria();
        String taskName = taskQueryDto.getTaskName();
        if(StringUtils.isNotBlank(taskName)){
            taskName = "%" + taskName + "%";
            criteria.andLike("taskName",taskName);
        }
        criteria.andEqualTo("facilitatorId",facilitatorId);
        criteria.andEqualTo("status",status);
        return imcInspectionTaskMapper.selectByExample(example);
    }


    /**
     * 判断巡检任务是否完成
     * @param taskId
     * @return
     */
    public Boolean isTaskFinish(Long taskId){
        Example example = new Example(ImcInspectionItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("inspectionTaskId",taskId);
        List<ImcInspectionItem> imcInspectionItemList = imcInspectionItemMapper.selectByExample(example);
        for(int i=0;i<imcInspectionItemList.size();i++){
            if(imcInspectionItemList.get(i).getStatus()<3){//如果还有巡检任务子项没完成
                return false;
            }
        }
        return true;//如果巡检任务子项都完成了，则巡检任务也完成了
    }
}
