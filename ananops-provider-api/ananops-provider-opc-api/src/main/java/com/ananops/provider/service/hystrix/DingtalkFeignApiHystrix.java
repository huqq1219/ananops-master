/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：DingtalkFeignApiHystrix.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.service.hystrix;


import com.ananops.provider.model.dto.robot.ChatRobotMsgDto;
import com.ananops.provider.service.DingtalkFeignApi;
import com.ananops.wrapper.Wrapper;
import org.springframework.stereotype.Component;


/**
 * The class Chat robot feign api hystrix.
 *
 * @author ananops.com @gmail.com
 */
@Component
public class DingtalkFeignApiHystrix implements DingtalkFeignApi {

	@Override
	public Wrapper<Boolean> sendChatRobotMsg(final ChatRobotMsgDto uacUserReqDto) {
		return null;
	}
}
