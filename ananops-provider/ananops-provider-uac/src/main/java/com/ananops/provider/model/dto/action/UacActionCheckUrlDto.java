/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：UacActionCheckUrlDto.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto.action;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * The class Uac menu check url dto.
 *
 * @author ananops.com@gmail.com
 */
@Data
@ApiModel(value = "UacActionCheckUrlDto")
public class UacActionCheckUrlDto implements Serializable {
	private static final long serialVersionUID = 839437721293973234L;
	/**
	 * 权限的id
	 */
	@ApiModelProperty(value = "权限的id")
	private Long actionId;
	/**
	 * 权限的url
	 */
	@ApiModelProperty(value = "权限地址")
	@NotBlank(message = "权限地址不能为空")
	private String url;


}
