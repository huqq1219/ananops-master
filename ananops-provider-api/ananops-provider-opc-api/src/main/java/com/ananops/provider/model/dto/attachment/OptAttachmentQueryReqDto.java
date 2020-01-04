/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：OptAttachmentQueryReqDto.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto.attachment;

import lombok.Data;

import java.io.Serializable;

/**
 * The class Opt attachment query req dto.
 *
 * @author ananops.com@gmail.com
 */
@Data
public class OptAttachmentQueryReqDto implements Serializable {

	private static final long serialVersionUID = 4781783608243094328L;
	private Long id;

	/**
	 * 附件流水号
	 */
	private String serialNo;

	/**
	 * 上传附件的相关业务流水号
	 */
	private String refNo;
}
