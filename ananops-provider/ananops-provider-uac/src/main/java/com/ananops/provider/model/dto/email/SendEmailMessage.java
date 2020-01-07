/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：SendEmailMessage.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto.email;

import lombok.Data;

import java.io.Serializable;

/**
 * The class Send email message.
 *
 * @author ananops.com@gmail.com
 */
@Data
public class SendEmailMessage implements Serializable {
	private static final long serialVersionUID = -8708881656765856624L;

	/**
	 * 登录名
	 */
	private String loginName;

	/**
	 * 手机号码
	 */
	private String email;

	/**
	 * 验证码
	 */
	private String emailCode;

	/**
	 * 邮件模板Code
	 */
	private String emailTemplateCode;

	/**
	 * Instantiates a new Send email message.
	 */
	public SendEmailMessage() {
	}

	/**
	 * Instantiates a new Send email message.
	 *
	 * @param email the email
	 */
	public SendEmailMessage(String email) {
		this.email = email;
	}
}
