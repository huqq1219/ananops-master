/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：TextDto.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto.robot;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * The class Text dto.
 *
 * @author ananops.com @gmail.com
 */
@Data
@ApiModel(value = "text类型")
public class TextDto implements Serializable {
	private static final long serialVersionUID = 8825625125019746717L;
	@ApiModelProperty(value = "消息内容", required = true)
	private String content;
}
