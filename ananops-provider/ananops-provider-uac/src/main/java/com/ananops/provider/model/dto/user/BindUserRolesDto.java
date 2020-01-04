/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：BindUserRolesDto.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * The class Bind user roles dto.
 *
 * @author ananops.com@gmail.com
 */
@ApiModel
@Data
public class BindUserRolesDto implements Serializable {

	private static final long serialVersionUID = -9149237379943908522L;
	@ApiModelProperty(value = "用户ID")
	private Long userId;

	@ApiModelProperty(value = "需要绑定的角色ID集合")
	private List<Long> roleIdList;
}
