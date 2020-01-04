/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：OptUploadFileByteInfoReqDto.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto.attachment;

import lombok.Data;

import java.io.Serializable;

/**
 * The class Opt upload file byte info req dto.
 *
 * @author ananops.com@gmail.com
 */
@Data
public class OptUploadFileByteInfoReqDto implements Serializable {
	private static final long serialVersionUID = 4695223041316258658L;

	/**
	 * 文件字节码
	 */
	private byte[] fileByteArray;

	/**
	 * 文件名
	 */
	private String fileName;

	/**
	 * 文件类型
	 */
	private String fileType;
}
