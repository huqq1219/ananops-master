package com.ananops.provider.mapper;

import com.ananops.core.mybatis.MyMapper;
import com.ananops.provider.model.domain.Approve;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApproveMapper extends MyMapper<Approve> {
    Approve selectTodoApproveByApproverIdAndObject(@Param("approverId") Long approverId, @Param("objectType") Integer objectType, @Param("objectId") Long objectId);
    
    List<Approve> selectByApproverId(@Param("approverId") Long approverId, @Param("verison")Integer version);

    List<Approve> selectByObject(@Param("objectType")Integer objectType, @Param("objectId")Long objectId);
}