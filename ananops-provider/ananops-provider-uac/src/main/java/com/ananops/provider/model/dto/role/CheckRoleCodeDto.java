/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：CheckRoleCodeDto.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto.role;

import lombok.Data;

import java.io.Serializable;

/**
 * The class Check role code dto.
 *
 * @author ananops.com @gmail.com
 */
@Data
public class CheckRoleCodeDto implements Serializable {
	private static final long serialVersionUID = 6369434659441735160L;

	private Long roleId;
	private String roleCode;
}
