/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：ElementImgUrlDto.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto.oss;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * The class Element img url dto.
 *
 * @author ananops.com @gmail.com
 */
@Data
@AllArgsConstructor
public class ElementImgUrlDto implements Serializable {
	public ElementImgUrlDto() {
	}

	private static final long serialVersionUID = -5800852605728871320L;
	/**
	 * 图片完整地址
	 */
	private String url;
	/**
	 * 图片名称
	 */
	private String name;
	/**
	 * 图片关联的附件ID
	 */
	private Long attachmentId;
}
