/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：TokenMainQueryDto.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto.token;


import com.ananops.base.dto.BaseQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The class Token main query dto.
 *
 * @author ananops.com @gmail.com
 */
@Data
@ApiModel
@EqualsAndHashCode(callSuper = true)
public class TokenMainQueryDto extends BaseQuery {
	private static final long serialVersionUID = -4003383211817581110L;
	private String loginName;

	private String userName;

	private Integer status;
}
