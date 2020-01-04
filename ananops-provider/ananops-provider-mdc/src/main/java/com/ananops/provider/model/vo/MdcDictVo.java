/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MdcDictVo.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.vo;

import com.ananops.base.dto.BaseTree;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The class Mdc dict vo.
 *
 * @author ananops.com @gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MdcDictVo extends BaseTree {
	private static final long serialVersionUID = -6759243808584390573L;
	/**
	 * 父数据字典名称
	 */
	private String parentDictName;
	/**
	 * 字典类型 -0 常量 - 1 文件夹
	 */
	private Integer type;

	/**
	 * 枚举编码
	 */
	private String dictKey;

	/**
	 * 枚举扩展字段
	 */
	private String extendKey;

	/**
	 * 枚举值
	 */
	private String dictValue;

	/**
	 * 字典编码
	 */
	private String dictCode;

	/**
	 * 字典名称
	 */
	private String dictName;

	/**
	 * 序号
	 */
	private Integer number;

	/**
	 * 状态
	 */
	private Integer status;

	/**
	 * 备注
	 */
	private String remark;
}
