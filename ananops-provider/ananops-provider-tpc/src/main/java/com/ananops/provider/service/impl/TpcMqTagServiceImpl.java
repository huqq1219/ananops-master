/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：TpcMqTagServiceImpl.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.service.impl;

import com.ananops.core.support.BaseService;
import com.ananops.provider.mapper.TpcMqTagMapper;
import com.ananops.provider.model.domain.TpcMqTag;
import com.ananops.provider.model.vo.TpcMqTagVo;
import com.ananops.provider.service.TpcMqConsumerService;
import com.ananops.provider.service.TpcMqTagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * The class Tpc mq tag service.
 *
 * @author ananops.com @gmail.com
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TpcMqTagServiceImpl extends BaseService<TpcMqTag> implements TpcMqTagService {

	@Resource
	private TpcMqTagMapper mdcMqTagMapper;
	@Resource
	private TpcMqConsumerService mdcMqConsumerService;

	@Override
	public List<TpcMqTagVo> listWithPage(TpcMqTag mdcMqTag) {
		return mdcMqTagMapper.listTpcMqTagVoWithPage(mdcMqTag);
	}

	@Override
	public int deleteTagById(Long tagId) {
		// 删除订阅的tag
		mdcMqConsumerService.deleteSubscribeTagByTagId(tagId);
		// 删除tag
		return mdcMqTagMapper.deleteByPrimaryKey(tagId);
	}
}
