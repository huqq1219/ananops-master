/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：RefreshTokenDto.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto.token;


import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * The class Refresh token dto.
 *
 * @author ananops.com @gmail.com
 */
@Data
public class RefreshTokenDto {
	@NotBlank
	private String refreshToken;
	@NotBlank
	private String accessToken;
}
