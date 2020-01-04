package com.ananops.provider.service;

import com.ananops.core.support.IService;
import com.ananops.provider.model.domain.PmcInspectDetail;

import java.util.List;

/**
 * Created By ChengHao On 2019/12/23
 */
public interface PmcInspectDetailsService extends IService<PmcInspectDetail> {
    /**
     * 编辑巡检细节
     * @param pmcInspectDetail
     * @return
     */
    int saveInspectDetail(PmcInspectDetail pmcInspectDetail);

    /**
     * 查找巡检详情
     * @param id
     * @return
     */
    PmcInspectDetail getInspectDetailById(Long id);

    /**
     * 获取巡检任务的详情列表
     * @param inspectTaskId
     * @return
     */
    List<PmcInspectDetail> getInspectDetailList(Long inspectTaskId);

    /**
     * 删除巡检详情
     * @param id
     * @return
     */
    int deleteDetailById(Long id);

    /**
     * 删除巡检任务下的所有巡检详情信息
     * @param taskId
     * @return
     */
    int deleteDetailByTaskId(Long taskId);
}
