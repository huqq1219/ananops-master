/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：ForgetCheckAnswerDto.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * The class Forget check answer dto.
 *
 * @author ananops.com@gmail.com
 */
@Data
@ApiModel
public class ForgetCheckAnswerDto implements Serializable {
	private static final long serialVersionUID = -4611532562847293450L;
	@ApiModelProperty(value = "登录名")
	private String loginName;
	@ApiModelProperty(value = "问题")
	private String email;
	@ApiModelProperty(value = "答案")
	private String answer;
}
