/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：GroupBindUserReqDto.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto.group;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * The class Group bind user req dto.
 *
 * @author ananops.com@gmail.com
 */
@Data
@ApiModel(value = "GroupBindUserReqDto")
public class GroupBindUserReqDto implements Serializable {
	private static final long serialVersionUID = 89217138744995863L;

	@ApiModelProperty(value = "组织ID")
	private Long groupId;

	@ApiModelProperty(value = "用户id")
	private List<Long> userIdList;
}
