/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：UacLoginService.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.service;


import com.ananops.provider.model.dto.user.LoginRespDto;

/**
 * The interface Uac login service.
 *
 * @author ananops.com@gmail.com
 */
public interface UacLoginService {

	/**
	 * Login after login resp dto.
	 *
	 * @param applicationId the application id
	 *
	 * @return the login resp dto
	 */
	LoginRespDto loginAfter(Long applicationId);

}
