/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：IdStatusDto.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * The class Id status dto.
 *
 * @author ananops.com@gmail.com
 */
@Data
@ApiModel
public class IdStatusDto implements Serializable {
	private static final long serialVersionUID = -1976690893998068416L;

	@ApiModelProperty(value = "用户ID", required = true)
	private Long id;
	@ApiModelProperty(value = "推送状态", required = true)
	private Integer status;
}
