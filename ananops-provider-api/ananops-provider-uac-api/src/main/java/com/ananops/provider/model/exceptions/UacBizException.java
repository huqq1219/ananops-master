/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：UacBizException.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.exceptions;

import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.base.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

/**
 * The class Uac biz exception.
 *
 * @author ananops.com @gmail.com
 */
@Slf4j
public class UacBizException extends BusinessException {

	private static final long serialVersionUID = -6552248511084911254L;

	/**
	 * Instantiates a new Uac rpc exception.
	 */
	public UacBizException() {
	}

	/**
	 * Instantiates a new Uac rpc exception.
	 *
	 * @param code      the code
	 * @param msgFormat the msg format
	 * @param args      the args
	 */
	public UacBizException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
		log.info("<== UacRpcException, code:" + this.code + ", message:" + super.getMessage());

	}

	/**
	 * Instantiates a new Uac rpc exception.
	 *
	 * @param code the code
	 * @param msg  the msg
	 */
	public UacBizException(int code, String msg) {
		super(code, msg);
		log.info("<== UacRpcException, code:" + this.code + ", message:" + super.getMessage());
	}

	/**
	 * Instantiates a new Uac rpc exception.
	 *
	 * @param codeEnum the code enum
	 */
	public UacBizException(ErrorCodeEnum codeEnum) {
		super(codeEnum.code(), codeEnum.msg());
		log.info("<== UacRpcException, code:" + this.code + ", message:" + super.getMessage());
	}

	/**
	 * Instantiates a new Uac rpc exception.
	 *
	 * @param codeEnum the code enum
	 * @param args     the args
	 */
	public UacBizException(ErrorCodeEnum codeEnum, Object... args) {
		super(codeEnum, args);
		log.info("<== OpcRpcException, code:" + this.code + ", message:" + super.getMessage());
	}
}
