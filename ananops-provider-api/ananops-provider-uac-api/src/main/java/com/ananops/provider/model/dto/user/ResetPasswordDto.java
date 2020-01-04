/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：ResetPasswordDto.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 重置密码.
 *
 * @author ananops.com@gmail.com
 */
@Data
@ApiModel
public class ResetPasswordDto implements Serializable {
	private static final long serialVersionUID = 4762153981220090958L;
	@ApiModelProperty("登录名")
	private String loginName;
	@ApiModelProperty(value = "旧密码")
	private String passwordOld;
	@ApiModelProperty(value = "新密码")
	private String passwordNew;
}
