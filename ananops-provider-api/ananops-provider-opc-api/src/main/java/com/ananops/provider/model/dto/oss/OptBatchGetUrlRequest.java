/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：OptBatchGetUrlRequest.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto.oss;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The class Opt batch get url request.
 *
 * @author ananops.com @gmail.com
 */
@Data
@ApiModel
@NoArgsConstructor
public class OptBatchGetUrlRequest {

	public OptBatchGetUrlRequest(String refNo) {
		this.refNo = refNo;
	}

	@ApiModelProperty(value = "文件流水号")
	private String refNo;

	@ApiModelProperty(value = "超时时间")
	private Long expires;

	@ApiModelProperty(value = "是否需要解密")
	private boolean encrypt;

}
