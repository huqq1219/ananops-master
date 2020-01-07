package com.ananops.provider.service;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.IService;
import com.ananops.provider.model.domain.PmcInspectTask;

import java.util.List;

/**
 * Created By ChengHao On 2019/12/5
 */
public interface PmcInspectTaskService extends IService<PmcInspectTask> {
    /**
     * 编辑巡检设备信息
     * @param pmcInspectTask
     */
    void saveDevice(PmcInspectTask pmcInspectTask, LoginAuthDto loginAuthDto);

    /**
     * 查询巡检任务
     * @param id
     * @return
     */
    PmcInspectTask getTaskById(Long id);

    /**
     * 获取某个项目的巡检任务
     * @param projectId
     * @return
     */
    List<PmcInspectTask> getTasksByProjectId(Long projectId);

    /**
     * 删除巡检任务
     * @param id
     */
    void deleteTaskById(Long id);

    /**
     *
     * @param projectId
     */
    void deleteTaskByProjectId(Long projectId);
}
