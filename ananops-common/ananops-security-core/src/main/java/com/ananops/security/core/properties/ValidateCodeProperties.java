/*
 * Copyright (c) 2019. ananops.net All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：ValidateCodeProperties.java
 * 创建人：ananops
 * 联系方式：ananops.net@gmail.com


 *  * 平台官网: http://ananops.com
 */

package com.ananops.security.core.properties;

import lombok.Data;

/**
 * 验证码配置
 *
 * @author ananops.net @gmail.com
 */
@Data
public class ValidateCodeProperties {

	/**
	 * 图片验证码配置
	 */
	private ImageCodeProperties image = new ImageCodeProperties();
	/**
	 * 短信验证码配置
	 */
	private SmsCodeProperties sms = new SmsCodeProperties();
	/**
	 * 邮箱验证码配置
	 */
	private EmailCodeProperties email = new EmailCodeProperties();

}
