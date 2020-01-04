/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：LoginReqDto.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * The class Login req dto.
 *
 * @author ananops.com@gmail.com
 */
@Data
@ApiModel(value = "登录参数")
public class LoginReqDto implements Serializable {
	private static final long serialVersionUID = -3455057005375331843L;
	/**
	 * 登录名
	 */
	@ApiModelProperty(value = "登录名")
	private String loginName;
	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码")
	private String loginPwd;
	/**
	 * 登录IP地址
	 */
	@ApiModelProperty(value = "ip")
	private String ip;
	/**
	 * 登录秘钥
	 */
	@ApiModelProperty(value = "登录秘钥")
	private String secToken;

	@ApiModelProperty(value = "系统ID")
	private Long applicationId;

}
  