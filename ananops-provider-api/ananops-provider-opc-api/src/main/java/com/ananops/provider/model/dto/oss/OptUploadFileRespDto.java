/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：OptUploadFileRespDto.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto.oss;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * The class Opt upload file resp dto.
 *
 * @author ananops.com@gmail.com
 */
@Data
@ApiModel(value = "OptUploadFileReqDto")
public class OptUploadFileRespDto implements Serializable {

	private static final long serialVersionUID = -8008720269972450739L;

	@ApiModelProperty(value = "附件ID")
	private Long attachmentId;
	@ApiModelProperty(value = "文件完整url")
	private String attachmentUrl;
	@ApiModelProperty(value = "文件名")
	private String attachmentName;
	@ApiModelProperty(value = "文件路径(等于七牛文件名)")
	private String attachmentPath;
	@ApiModelProperty(value = "文件类型")
	private String fileType;
	@ApiModelProperty(value = "关联业务单号")
	private String refNo;

}
