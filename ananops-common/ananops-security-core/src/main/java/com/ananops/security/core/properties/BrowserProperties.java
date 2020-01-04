/*
 * Copyright (c) 2019. ananops.net All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：BrowserProperties.java
 * 创建人：ananops
 * 联系方式：ananops.net@gmail.com


 *  * 平台官网: http://ananops.com
 */

package com.ananops.security.core.properties;

import lombok.Data;

/**
 * 浏览器环境配置项
 *
 * @author ananops.net @gmail.com
 */
@Data
public class BrowserProperties {

	/**
	 * session管理配置项
	 */
	private SessionProperties session = new SessionProperties();
	/**
	 * '记住我'功能的有效时间，默认1小时
	 */
	private int rememberMeSeconds = 3600;
	/**
	 * 社交登录，如果需要用户注册，跳转的页面
	 */
	private String signUpUrl = "/pc-signUp.html";
	/**
	 * 登录响应的方式，默认是json
	 */
	private LoginResponseType signInResponseType = LoginResponseType.JSON;
	/**
	 * 登录成功后跳转的地址，如果设置了此属性，则登录成功后总是会跳到这个地址上。
	 * 只在signInResponseType为REDIRECT时生效
	 */
	private String singInSuccessUrl;

}
