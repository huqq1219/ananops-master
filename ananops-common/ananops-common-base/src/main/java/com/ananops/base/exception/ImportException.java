/*
 * Copyright (c) 2019. ananops.net All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：ImportException.java
 * 创建人：ananops
 * 联系方式：ananops.net@gmail.com


 *  * 平台官网: http://ananops.com
 */
package com.ananops.base.exception;


/**
 * The class Import exception.
 *
 * @author ananops.net@gmail.com
 */
public class ImportException extends RuntimeException {

	private static final long serialVersionUID = -4740091660440744697L;

	/**
	 * Instantiates a new Import exception.
	 *
	 * @param message the message
	 */
	public ImportException(String message) {
		super(message);
	}
}
