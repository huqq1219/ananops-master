/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：EmailService.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.service;

import com.ananops.provider.model.dto.email.SendEmailMessage;

/**
 * The interface Email service.
 *
 * @author ananops.com @gmail.com
 */
public interface EmailService {
	/**
	 * 重置密码发送邮件.
	 *
	 * @param email the email
	 */
	void submitResetPwdEmail(String email);

	/**
	 * 发送验证码
	 *
	 * @param sendEmailMessage the send email message
	 * @param loginName        the login name
	 */
	void sendEmailCode(SendEmailMessage sendEmailMessage, String loginName);

	/**
	 * 校验验证码 返回 token 用户最后修改密码使用
	 *
	 * @param sendEmailMessage the send email message
	 * @param loginName        the login name
	 */
	void checkEmailCode(SendEmailMessage sendEmailMessage, String loginName);
}
