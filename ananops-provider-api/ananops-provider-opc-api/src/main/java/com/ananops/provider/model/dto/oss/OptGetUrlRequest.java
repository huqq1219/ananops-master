/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：OptGetUrlRequest.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto.oss;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * The class Opt get url request.
 *
 * @author ananops.com@gmail.com
 */
@Data
@ApiModel
public class OptGetUrlRequest {
	@ApiModelProperty(value = "附件ID")
	private Long attachmentId;

	@ApiModelProperty(value = "超时时间")
	private Long expires;

	@ApiModelProperty(value = "是否需要解密")
	private boolean encrypt;

	@ApiModelProperty(value = "附件ID集合")
	private List<Long> attachmentIdList;

}
