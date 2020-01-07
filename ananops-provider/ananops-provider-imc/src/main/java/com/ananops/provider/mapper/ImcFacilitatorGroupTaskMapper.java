package com.ananops.provider.mapper;

import com.ananops.provider.model.domain.ImcFacilitatorGroupTask;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;
@Mapper
@Component
public interface ImcFacilitatorGroupTaskMapper extends BaseMapper<ImcFacilitatorGroupTask> {
}