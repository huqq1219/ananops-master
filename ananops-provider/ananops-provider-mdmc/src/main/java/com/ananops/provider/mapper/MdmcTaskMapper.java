package com.ananops.provider.mapper;

import com.ananops.core.mybatis.MyMapper;
import com.ananops.provider.model.domain.MdmcTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MdmcTaskMapper extends MyMapper<MdmcTask> {
    @Select("select * from task left join task_item on task.`id`=task_item.task_id where task_item.maintainer_id = #{maintainer_id} and task.`status`=#{status}")
    List<MdmcTask> selectByMaintainerIdAndStatus(@Param("maintainer_id") Long maintainer_id, @Param("status") Integer status);

   @Select("select * from task where task.`user_id`=#{user_id}")
    List<MdmcTask> selectByUserId(@Param("user_id") Long user_id);
}