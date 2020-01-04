package com.ananops.provider.mapper;

import com.ananops.provider.base.MyMapper;
import com.ananops.provider.model.domain.Basebill;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface BasebillMapper extends MyMapper<Basebill> {
}