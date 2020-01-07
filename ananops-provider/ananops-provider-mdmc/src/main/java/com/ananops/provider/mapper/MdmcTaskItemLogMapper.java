package com.ananops.provider.mapper;

import com.ananops.core.mybatis.MyMapper;
import com.ananops.provider.model.domain.MdmcTaskItemLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MdmcTaskItemLogMapper extends MyMapper<MdmcTaskItemLog> {

    /*获取单个报修单的处理进度列表，xml没写*/
    List<MdmcTaskItemLog> selectByTaskItemId(Long taskItem_id);
}