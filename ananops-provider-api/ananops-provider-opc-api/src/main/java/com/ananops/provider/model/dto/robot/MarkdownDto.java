/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MarkdownDto.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto.robot;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * The class Markdown dto.
 *
 * @author ananops.com @gmail.com
 */
@Data
@ApiModel(value = "link类型")
public class MarkdownDto implements Serializable {
	private static final long serialVersionUID = 5169795915361197484L;
	/**
	 * 首屏会话透出的展示内容
	 */
	@ApiModelProperty(value = "首屏会话透出的展示内容", required = true)
	private String title;
	/**
	 * markdown格式的消息
	 */
	@ApiModelProperty(value = "markdown格式的消息", required = true)
	private String text;
}
