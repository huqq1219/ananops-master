/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：UacMqMessageApiHystrix.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.service.hystrix;


import com.github.pagehelper.PageInfo;
import com.ananops.base.dto.MessageQueryDto;
import com.ananops.base.dto.MqMessageVo;
import com.ananops.provider.model.service.UacMqMessageFeignApi;
import com.ananops.wrapper.Wrapper;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * The class Uac mq message api hystrix.
 *
 * @author ananops.com @gmail.com
 */
@Component
public class UacMqMessageApiHystrix implements UacMqMessageFeignApi {

	@Override
	public Wrapper<List<String>> queryMessageKeyList(final List<String> messageKeyList) {
		return null;
	}

	@Override
	public Wrapper<PageInfo<MqMessageVo>> queryMessageListWithPage(final MessageQueryDto messageQueryDto) {
		return null;
	}

}
