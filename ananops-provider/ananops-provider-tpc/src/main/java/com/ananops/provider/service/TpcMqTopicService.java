/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：TpcMqTopicService.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.service;

import com.ananops.core.support.IService;
import com.ananops.provider.model.domain.TpcMqTopic;
import com.ananops.provider.model.vo.TpcMqTopicVo;

import java.util.List;

/**
 * The interface Tpc mq topic service.
 *
 * @author ananops.com @gmail.com
 */
public interface TpcMqTopicService extends IService<TpcMqTopic> {
	/**
	 * 查询MQ topic列表.
	 *
	 * @param mdcMqTopic the mdc mq topic
	 *
	 * @return the list
	 */
	List<TpcMqTopicVo> listWithPage(TpcMqTopic mdcMqTopic);

}
