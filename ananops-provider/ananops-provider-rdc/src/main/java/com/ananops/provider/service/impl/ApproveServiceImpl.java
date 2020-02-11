package com.ananops.provider.service.impl;

import com.ananops.core.support.BaseService;
import com.ananops.provider.mapper.ApproveMapper;
import com.ananops.provider.model.domain.Approve;
import com.ananops.provider.service.ApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApproveServiceImpl extends BaseService<Approve> implements ApproveService {
    @Autowired
    ApproveMapper approveMapper;
    
    public Approve getApproveByApproverIdAndObject(Long approveId, Integer objectType, Long objectId) {
        return approveMapper.selectTodoApproveByApproverIdAndObject(approveId, objectType, objectId);
    }
    
    public List<Approve> selectByApproverId(Long approverId, Integer version){
        return approveMapper.selectByApproverId(approverId, version);
    }
    
    public boolean isExist(Approve approve) {
        return selectOne(approve) != null;
    }

    public List<Approve> selectByObject(Integer objectType, Long objectId){
        return approveMapper.selectByObject(objectType, objectId);
    }
}
