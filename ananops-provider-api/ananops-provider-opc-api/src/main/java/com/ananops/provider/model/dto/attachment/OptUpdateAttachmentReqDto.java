/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：OptUpdateAttachmentReqDto.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto.attachment;

import lombok.Data;

import java.io.Serializable;

/**
 * The class Opt update attachment req dto.
 *
 * @author ananops.com@gmail.com
 */
@Data
public class OptUpdateAttachmentReqDto implements Serializable {

	private static final long serialVersionUID = -1727131719075160349L;

	/**
	 * 附件流水号
	 */
	private String serialNo;

	/**
	 * 上传附件的相关业务流水号
	 */
	private String refNo;

	private Long userId;

	private String userName;

}
