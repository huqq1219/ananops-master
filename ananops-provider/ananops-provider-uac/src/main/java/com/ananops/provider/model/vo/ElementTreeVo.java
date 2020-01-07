/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：ElementTreeVo.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * The class Element tree vo.
 *
 * @author ananops.com@gmail.com
 */
@Data
public class ElementTreeVo implements Serializable {
	private static final long serialVersionUID = -7266614376023024646L;

	private String label;

	private List<ElementTreeVo> children;
}
