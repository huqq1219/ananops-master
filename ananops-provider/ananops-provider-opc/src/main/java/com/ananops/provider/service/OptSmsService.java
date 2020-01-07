/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：OptSmsService.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;

/**
 * The interface Opt sms service.
 *
 * @author ananops.com@gmail.com
 */
public interface OptSmsService {
	/**
	 * Send sms send sms response.
	 *
	 * @param sendSmsRequest the send sms request
	 *
	 * @return the send sms response
	 */
	SendSmsResponse sendSms(SendSmsRequest sendSmsRequest);
}
