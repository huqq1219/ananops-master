/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：CheckGroupNameDto.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto.group;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * The class Check group name dto.
 *
 * @author ananops.com@gmail.com
 */
@Data
@ApiModel
public class CheckGroupNameDto {

	@ApiModelProperty(value = "组织ID")
	private Long groupId;

	@ApiModelProperty(value = "组织名称")
	private String groupName;
}
