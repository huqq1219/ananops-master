package com.ananops.provider.mapper;

import com.ananops.core.mybatis.MyMapper;
import com.ananops.provider.model.domain.PmcProject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PmcProjectMapper extends MyMapper<PmcProject> {
    List<PmcProject> getProjectByUserId(Long userId);

}