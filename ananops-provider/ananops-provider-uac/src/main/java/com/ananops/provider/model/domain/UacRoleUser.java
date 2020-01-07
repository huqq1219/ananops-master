/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：UacRoleUser.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * The class Uac role user.
 *
 * @author ananops.com@gmail.com
 */
@Data
@Table(name = "an_uac_role_user")
@Alias(value = "uacRoleUser")
public class UacRoleUser implements Serializable {
	private static final long serialVersionUID = -4598936929315554832L;
	@Id
	@Column(name = "role_id")
	private Long roleId;

	@Id
	@Column(name = "user_id")
	private Long userId;
}