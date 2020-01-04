/*
 * Copyright (c) 2019. ananops.net All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：Oauth2ClientProperties.java
 * 创建人：ananops
 * 联系方式：ananops.net@gmail.com


 *  * 平台官网: http://ananops.com
 */

package com.ananops.security.feign;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The class Oauth 2 client properties.
 *
 * @author ananops.net @gmail.com
 */
@Data
@ConfigurationProperties(prefix = "ananops.oauth2.client")
public class Oauth2ClientProperties {
	private String id;
	private String accessTokenUrl;
	private String clientId;
	private String clientSecret;
	private String clientAuthenticationScheme;
}

