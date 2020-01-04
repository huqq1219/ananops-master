/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：ActionVo.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.vo;

import com.ananops.base.dto.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The class Action vo.
 *
 * @author ananops.com @gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class ActionVo extends BaseVo {
	private static final long serialVersionUID = 2019306004446193459L;
	/**
	 * 资源路径
	 */
	private String url;

	/**
	 * 权限名称
	 */
	private String actionName;

	/**
	 * 权限
	 */
	private String actionCode;

	/**
	 * 状态
	 */
	private String status;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 菜单ID
	 */
	private Long menuId;

	/**
	 * 菜单名称
	 */
	private String menuName;
}
