/*
 * Copyright (c) 2019. ananops.net All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：OAuth2Properties.java
 * 创建人：ananops
 * 联系方式：ananops.net@gmail.com


 *  * 平台官网: http://ananops.com
 */

package com.ananops.security.core.properties;

import lombok.Data;

/**
 * The class O auth 2 properties.
 *
 * @author ananops.net @gmail.com
 */
@Data
public class OAuth2Properties {

	/**
	 * 使用jwt时为token签名的秘钥
	 */
	private String jwtSigningKey = "ananops";
	/**
	 * 客户端配置
	 */
	private OAuth2ClientProperties[] clients = {};

}
