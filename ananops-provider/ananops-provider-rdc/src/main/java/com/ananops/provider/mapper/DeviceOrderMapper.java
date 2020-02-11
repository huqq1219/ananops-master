package com.ananops.provider.mapper;

import com.ananops.core.mybatis.MyMapper;
import com.ananops.provider.model.domain.DeviceOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeviceOrderMapper extends MyMapper<DeviceOrder> {
    /**
     * 根据审核人编号和审核状态获取备品备件订单列表
     * @param approverId 当前审核人编号
     * @param version 审核状态（0审核结束 1审核中 null所有）
     * @return
     */
    List<DeviceOrder> selectByApproverIdAndVersion(@Param("approverId") Long approverId, @Param("version") Integer version);
    
    /**
     * 根据审核人编号和审核状态获取备品备件订单的数量
     * @param approverId 当前审核人编号
     * @param version 审核状态（0审核结束 1审核中 null所有
     * @return
     */
    int selectCountByApproverIdAndVersion(@Param("approverId") Long approverId, @Param("version") Integer version);
    
    
    DeviceOrder selectByObject(@Param("objectType")Integer objectType, @Param("objectId")Long objectId);

    List<DeviceOrder> selectAllByObject(@Param("objectType")Integer objectType, @Param("objectId")Long objectId);
}