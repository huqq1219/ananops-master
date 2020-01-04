/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：RoleVo.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.vo;

import com.ananops.base.dto.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The class Role vo.
 *
 * @author ananops.com @gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleVo extends BaseVo {

	private static final long serialVersionUID = 3819529748816533170L;

	/**
	 * 角色编码
	 */
	private String roleCode;

	private String roleName;

	/**
	 * 状态
	 */
	private String status;

	/**
	 * 备注
	 */
	private String remark;
}