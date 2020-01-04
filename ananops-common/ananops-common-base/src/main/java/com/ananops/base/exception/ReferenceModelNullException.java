/*
 * Copyright (c) 2019. ananops.net All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：ReferenceModelNullException.java
 * 创建人：ananops
 * 联系方式：ananops.net@gmail.com


 *  * 平台官网: http://ananops.com
 */
package com.ananops.base.exception;

/**
 * The class Reference model null exception.
 *
 * @author ananops.net@gmail.com
 */
public class ReferenceModelNullException extends RuntimeException {
	private static final long serialVersionUID = -318154770875589045L;

	/**
	 * Instantiates a new Reference model null exception.
	 *
	 * @param message the message
	 */
	public ReferenceModelNullException(String message) {
		super(message);
	}
}
