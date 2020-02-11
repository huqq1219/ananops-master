package com.ananops.provider.service;

import com.ananops.core.support.IService;
import com.ananops.provider.model.domain.Approve;

import java.util.List;

public interface ApproveService extends IService<Approve> {
    
    /**
     * 获取用户关于特定类型的审批记录
     * @param approveId
     * @param objectType
     * @param objectId
     * @return
     */
    Approve getApproveByApproverIdAndObject(Long approveId, Integer objectType, Long objectId);
    
    /**
     * 获取用户的所有（1.待处理 0.已处理）审批记录
     * @param approverId
     * @param version
     * @return
     */
    List<Approve> selectByApproverId(Long approverId, Integer version);
    
    
    boolean isExist(Approve approve);

    List<Approve> selectByObject(Integer objectType, Long objectId);
}
