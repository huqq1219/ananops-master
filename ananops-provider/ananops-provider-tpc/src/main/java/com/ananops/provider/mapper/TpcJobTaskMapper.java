/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：TpcJobTaskMapper.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.mapper;

import com.ananops.core.mybatis.MyMapper;
import com.ananops.provider.model.domain.TpcJobTask;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The interface Tpc job task mapper.
 *
 * @author ananops.com @gmail.com
 */
@Mapper
@Component
public interface TpcJobTaskMapper extends MyMapper<TpcJobTask> {
}