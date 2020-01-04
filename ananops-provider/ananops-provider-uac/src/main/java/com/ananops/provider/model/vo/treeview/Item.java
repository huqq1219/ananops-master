/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：Item.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.vo.treeview;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * The class Item.
 *
 * @author ananops.com@gmail.com
 */
@Data
@ApiModel
public class Item {

	private Long id;
	/**
	 * 节点的名字
	 */
	private String text;

	/**
	 * 节点的类型："item":文件 "folder":目录
	 */
	private String type;

	/**
	 * 子节点的信息
	 */
	private AdditionalParameters additionalParameters;

}
