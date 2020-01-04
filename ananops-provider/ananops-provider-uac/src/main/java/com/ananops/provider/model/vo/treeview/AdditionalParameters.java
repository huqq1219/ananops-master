/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：AdditionalParameters.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.vo.treeview;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Map;

/**
 * The class Additional parameters.
 *
 * @author ananops.com@gmail.com
 */
@Data
@ApiModel
public class AdditionalParameters {
	/**
	 * 子节点列表
	 */
	private Map<String, Item> children;

	/**
	 * 节点的Id
	 */
	private Long id;

	/**
	 * 是否有选中属性
	 */
	@JsonProperty("item-selected")
	private boolean itemSelected;

}
