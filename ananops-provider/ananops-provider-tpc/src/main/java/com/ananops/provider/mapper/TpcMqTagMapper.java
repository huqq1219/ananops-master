/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：TpcMqTagMapper.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.mapper;

import com.ananops.core.mybatis.MyMapper;
import com.ananops.provider.model.domain.TpcMqTag;
import com.ananops.provider.model.vo.TpcMqTagVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The interface Tpc mq tag mapper.
 *
 * @author ananops.com @gmail.com
 */
@Mapper
@Component
public interface TpcMqTagMapper extends MyMapper<TpcMqTag> {
	/**
	 * List tpc mq tag vo with page list.
	 *
	 * @param tpcMqTag the tpc mq tag
	 *
	 * @return the list
	 */
	List<TpcMqTagVo> listTpcMqTagVoWithPage(TpcMqTag tpcMqTag);
}