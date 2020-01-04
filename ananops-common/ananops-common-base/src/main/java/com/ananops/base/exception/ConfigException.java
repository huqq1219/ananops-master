/*
 * Copyright (c) 2019. ananops.net All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：ConfigException.java
 * 创建人：ananops
 * 联系方式：ananops.net@gmail.com


 *  * 平台官网: http://ananops.com
 */
package com.ananops.base.exception;

/**
 * The class Config exception.
 *
 * @author ananops.net@gmail.com
 */
public class ConfigException extends RuntimeException {

	private static final long serialVersionUID = 6480772904575978373L;

	/**
	 * Instantiates a new Config exception.
	 *
	 * @param message the message
	 */
	public ConfigException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new Config exception.
	 */
	public ConfigException() {

	}
}
