/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：UacPermissionService.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.service;


import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Uac permission service.
 *
 * @author ananops.com @gmail.com
 */
public interface UacPermissionService {

	/**
	 * Has permission boolean.
	 *
	 * @param authentication the authentication
	 * @param request        the request
	 *
	 * @return the boolean
	 */
	boolean hasPermission(Authentication authentication, HttpServletRequest request);
}
