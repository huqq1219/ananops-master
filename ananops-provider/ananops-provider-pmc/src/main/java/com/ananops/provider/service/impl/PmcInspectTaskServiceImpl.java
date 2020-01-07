package com.ananops.provider.service.impl;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.core.support.BaseService;
import com.ananops.provider.exception.PmcBizException;
import com.ananops.provider.mapper.PmcInspectTaskMapper;
import com.ananops.provider.mapper.PmcProjectMapper;
import com.ananops.provider.model.domain.PmcInspectTask;
import com.ananops.provider.model.domain.PmcProject;
import com.ananops.provider.service.PmcInspectDetailsService;
import com.ananops.provider.service.PmcInspectTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created By ChengHao On 2019/12/5
 */
@Service
@Slf4j
@Transactional
public class PmcInspectTaskServiceImpl extends BaseService<PmcInspectTask> implements PmcInspectTaskService {
    @Resource
    PmcInspectTaskMapper pmcInspectTaskMapper;
    @Resource
    PmcProjectMapper pmcProjectMapper;
    @Resource
    PmcInspectDetailsService pmcInspectDetailService;

    @Override
    public void saveDevice(PmcInspectTask pmcInspectTask, LoginAuthDto loginAuthDto) {
        pmcInspectTask.setUpdateInfo(loginAuthDto);
        if (pmcInspectTask.isNew()) {  //新增巡检任务
            PmcProject pmcProject = pmcProjectMapper.selectByPrimaryKey(pmcInspectTask.getProjectId());
            if (pmcProject == null) {
                throw new PmcBizException(ErrorCodeEnum.PMC10081023, pmcInspectTask.getProjectId());
            }
            pmcInspectTask.setProjectName(pmcProject.getProjectName());
            pmcInspectTask.setId(super.generateId());
            pmcInspectTaskMapper.insertSelective(pmcInspectTask);
        } else {                         //更新
            Integer result = pmcInspectTaskMapper.updateByPrimaryKeySelective(pmcInspectTask);
            if (result < 1) {
                throw new PmcBizException(ErrorCodeEnum.PMC10081021, pmcInspectTask.getId());
            }
        }
    }

    @Override
    public PmcInspectTask getTaskById(Long id) {
        return pmcInspectTaskMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PmcInspectTask> getTasksByProjectId(Long projectId) {
        Example example = new Example(PmcInspectTask.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", projectId);
        return pmcInspectTaskMapper.selectByExample(example);
    }

    @Override
    public void deleteTaskById(Long id) {
        if (pmcInspectDetailService.getInspectDetailList(id) != null) {
            pmcInspectDetailService.deleteDetailByTaskId(id);  //删除级联的任务详情
        }
        Integer result = pmcInspectTaskMapper.deleteByPrimaryKey(id);
        if (result < 1) {
            throw new PmcBizException(ErrorCodeEnum.PMC10081022, id);
        }
    }

    @Override
    public void deleteTaskByProjectId(Long projectId) {
        List<PmcInspectTask> pmcInspectTasks = this.getTasksByProjectId(projectId);
        if (pmcInspectTasks != null) { //删除级联的任务详情
            for (PmcInspectTask pmcInspectTask : pmcInspectTasks) {
                pmcInspectDetailService.deleteDetailByTaskId(pmcInspectTask.getId());
            }
        }
        Example example = new Example(PmcInspectTask.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", projectId);
        pmcInspectTaskMapper.deleteByExample(example);
    }


}
