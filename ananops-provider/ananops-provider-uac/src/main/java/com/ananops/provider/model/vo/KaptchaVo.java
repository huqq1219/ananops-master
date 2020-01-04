/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：KaptchaVo.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.vo;

import io.swagger.annotations.Api;
import lombok.Data;

/**
 * The class Kaptcha vo.
 *
 * @author ananops.com @gmail.com
 */
@Data
@Api
public class KaptchaVo {
	private String cookieCode;
	private String base64Code;
	private String kaptchaCode;
}
