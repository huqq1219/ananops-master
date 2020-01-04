/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：OpcRpcService.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.service;

/**
 * The interface Opc rpc service.
 *
 * @author ananops.com @gmail.com
 */
public interface OpcRpcService {

	/**
	 * Gets location by id.
	 *
	 * @param addressId the address id
	 *
	 * @return the location by id
	 */
	String getLocationById(String addressId);
}
