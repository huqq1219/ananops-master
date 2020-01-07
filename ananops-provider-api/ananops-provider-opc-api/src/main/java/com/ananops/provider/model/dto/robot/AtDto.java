/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：AtDto.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto.robot;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * The class At dto.
 *
 * @author ananops.com @gmail.com
 */
@Data
@ApiModel(value = "AtDto")
public class AtDto implements Serializable {
	private static final long serialVersionUID = 2344037651462081640L;
	/**
	 * 被@人的手机号
	 */
	@ApiModelProperty(value = "被@人的手机号")
	private String[] atMobiles;
	/**
	 * \@所有人时:true,否则为:false
	 */
	@ApiModelProperty(value = "@所有人时:true,否则为:false")
	private boolean isAtAll;
}
