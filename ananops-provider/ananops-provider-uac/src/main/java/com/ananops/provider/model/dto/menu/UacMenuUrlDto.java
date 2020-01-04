/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：UacMenuUrlDto.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * The class Uac menu url dto.
 *
 * @author ananops.com@gmail.com
 */
@Data
@ApiModel(value = "根据URL查询菜单Dto")
public class UacMenuUrlDto implements Serializable {


	private static final long serialVersionUID = 456904676277011675L;
	@ApiModelProperty(value = "系统Id", required = true)
	private String systemId;

	@ApiModelProperty(value = "用户ID", required = true)
	private Long userId;

	@ApiModelProperty(value = "url", required = true)
	private String url;
}
