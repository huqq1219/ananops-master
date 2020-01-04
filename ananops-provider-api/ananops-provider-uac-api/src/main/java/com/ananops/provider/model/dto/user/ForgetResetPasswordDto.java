/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：ForgetResetPasswordDto.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 忘记密码.
 *
 * @author ananops.com@gmail.com
 */
@Data
@ApiModel
public class ForgetResetPasswordDto implements Serializable {
	private static final long serialVersionUID = 5478700873789068921L;
	@ApiModelProperty(value = "登录名")
	private String loginName;
	@ApiModelProperty(value = "密码")
	private String loginPwd;
	@ApiModelProperty(value = "邮箱")
	private String email;
	@ApiModelProperty(value = "验证码")
	private String emailCode;
	@ApiModelProperty(value = "token")
	private String forgetToken;
}
