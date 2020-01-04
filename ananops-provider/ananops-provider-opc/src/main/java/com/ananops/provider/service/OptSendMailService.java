/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：OptSendMailService.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.service;

import java.util.Map;
import java.util.Set;

/**
 * The interface Opt send mail service.
 *
 * @author ananops.com@gmail.com
 */
public interface OptSendMailService {
	/**
	 * Send simple mail int.
	 *
	 * @param subject the subject
	 * @param text    the text
	 * @param to      the to
	 *
	 * @return the int
	 */
	int sendSimpleMail(String subject, String text, Set<String> to);

	/**
	 * Send template mail int.
	 *
	 * @param subject the subject
	 * @param text    the text
	 * @param to      the to
	 *
	 * @return the int
	 */
	int sendTemplateMail(String subject, String text, Set<String> to);

	/**
	 * Send template mail int.
	 *
	 * @param model            the model
	 * @param templateLocation the template location
	 * @param subject          the subject
	 * @param to               the to
	 *
	 * @return the int
	 */
	int sendTemplateMail(Map<String, Object> model, String templateLocation, String subject, Set<String> to);
}
