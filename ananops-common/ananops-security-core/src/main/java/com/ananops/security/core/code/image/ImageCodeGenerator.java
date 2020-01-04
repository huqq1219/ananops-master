/*
 * Copyright (c) 2019. ananops.net All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：ImageCodeGenerator.java
 * 创建人：ananops
 * 联系方式：ananops.net@gmail.com


 *  * 平台官网: http://ananops.com
 */

package com.ananops.security.core.code.image;

import com.google.code.kaptcha.Producer;
import com.ananops.security.core.properties.SecurityProperties;
import com.ananops.security.core.code.ValidateCodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.image.BufferedImage;

/**
 * 默认的图片验证码生成器
 *
 * @author ananops.net @gmail.com
 */
public class ImageCodeGenerator implements ValidateCodeGenerator {

	private SecurityProperties securityProperties;
	private Producer captchaProducer;

	/**
	 * 生成图片验证码.
	 *
	 * @param request the request
	 *
	 * @return the image code
	 */
	@Override
	public ImageCode generate(ServletWebRequest request) {
		String kaptchaCode = captchaProducer.createText();
		BufferedImage image = captchaProducer.createImage(kaptchaCode);
		return new ImageCode(image, kaptchaCode, securityProperties.getCode().getImage().getExpireIn());
	}

	/**
	 * Sets security properties.
	 *
	 * @param securityProperties the security properties
	 */
	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

	/**
	 * Sets captcha producer.
	 *
	 * @param captchaProducer the captcha producer
	 */
	public void setCaptchaProducer(Producer captchaProducer) {
		this.captchaProducer = captchaProducer;
	}


}
