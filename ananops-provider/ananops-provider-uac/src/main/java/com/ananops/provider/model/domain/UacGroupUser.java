/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：UacGroupUser.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * The class Uac group user.
 *
 * @author ananops.com@gmail.com
 */
@Data
@Table(name = "an_uac_group_user")
@Alias(value = "uacGroupUser")
public class UacGroupUser implements Serializable {
	private static final long serialVersionUID = 5167709959453148144L;

	/**
	 * 角色ID
	 */
	@Column(name = "group_id")
	private Long groupId;

	/**
	 * 菜单ID
	 */
	@Column(name = "user_id")
	private Long userId;
}