package com.ananops.provider.service.impl;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.BaseService;
import com.ananops.provider.mapper.ImcInspectionTaskLogMapper;
import com.ananops.provider.model.domain.ImcInspectionTaskLog;
import com.ananops.provider.model.dto.TaskLogQueryDto;
import com.ananops.provider.model.enums.TaskStatusEnum;
import com.ananops.provider.model.vo.TaskLogVo;
import com.ananops.provider.service.ImcInspectionTaskLogService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rongshuai on 2019/11/28 15:30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ImcInspectionTaskLogServiceImpl extends BaseService<ImcInspectionTaskLog> implements ImcInspectionTaskLogService {
    @Resource
    ImcInspectionTaskLogMapper imcInspectionTaskLogMapper;

    public Integer createInspectionTaskLog(ImcInspectionTaskLog imcInspectionTaskLog, LoginAuthDto loginAuthDto){//创建一条巡检任务的日志
        Long taskLogId = super.generateId();
        imcInspectionTaskLog.setUpdateInfo(loginAuthDto);
        imcInspectionTaskLog.setId(taskLogId);
        return imcInspectionTaskLogMapper.insert(imcInspectionTaskLog);
    }

    public List<TaskLogVo> getTaskLogsByTaskId(TaskLogQueryDto taskLogQueryDto){//根据巡检任务的id，查询所有的任务日志
        ImcInspectionTaskLog imcInspectionTaskLog = new ImcInspectionTaskLog();
        Long taskId = taskLogQueryDto.getTaskId();
        imcInspectionTaskLog.setTaskId(taskId);
        String orderBy = "status_timestamp asc";//设置为根据时间戳进行排序
        imcInspectionTaskLog.setOrderBy(orderBy);
        PageHelper.startPage(taskLogQueryDto.getPageNum(),taskLogQueryDto.getPageSize());
        List<TaskLogVo> taskLogVoList = imcInspectionTaskLogMapper.queryTaskLogListWithPage(imcInspectionTaskLog);
        for(int i=0;i<taskLogVoList.size();i++){
            int status = taskLogVoList.get(i).getStatus();
            taskLogVoList.get(i).setStatusMsg(TaskStatusEnum.getStatusMsg(status));
        }
        return taskLogVoList;
    }

}
