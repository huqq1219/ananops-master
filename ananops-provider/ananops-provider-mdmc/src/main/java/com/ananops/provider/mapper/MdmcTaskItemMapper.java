package com.ananops.provider.mapper;

import com.ananops.core.mybatis.MyMapper;
import com.ananops.provider.model.domain.MdmcTaskItem;
import com.ananops.provider.model.dto.MdmcAddTaskItemDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MdmcTaskItemMapper extends MyMapper<MdmcTaskItem> {

    /*获取单个故障信息*/
    MdmcTaskItem selectByPrimaryKey(Long id);

    /*获取单个设备信息*/
    MdmcTaskItem selectByDeviceId(Long device_id);

    /*用户获取某一状态下报修单列表,xml没写*/
    List<MdmcTaskItem> selectByCreatorIdAndStatus(Long creator_id, Integer status);

    /*获取报修单列表，xml没写*/
    List<MdmcTaskItem> selectByCreatorId(Long creator_id);

    /*获取维修单列表，xml没写*/
    List<MdmcTaskItem> selectByMaintainerId(Long maintainer_id);

    @Select("select * from task_item where `maintainer_id`=#{maintainer_id} and `status`=#{status}")
    List<MdmcTaskItem> selectByMaintainerIdAndStatus(@Param("maintainer_id") Long maintainer_id, @Param("status") Integer status);

    /*插入一条工单记录*/
    Void addTaskItem(MdmcAddTaskItemDto taskItemDto);


}