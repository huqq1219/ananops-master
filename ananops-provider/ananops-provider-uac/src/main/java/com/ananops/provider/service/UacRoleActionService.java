/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：UacRoleActionService.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.service;

import com.ananops.core.support.IService;
import com.ananops.provider.model.domain.UacRoleAction;

import java.util.List;
import java.util.Set;

/**
 * The interface Uac role action service.
 *
 * @author ananops.com @gmail.com
 */
public interface UacRoleActionService extends IService<UacRoleAction> {
	/**
	 * List by role id list.
	 *
	 * @param roleId the role id
	 *
	 * @return the list
	 */
	List<UacRoleAction> listByRoleId(Long roleId);

	/**
	 * Delete by role id.
	 *
	 * @param roleId the role id
	 */
	void deleteByRoleId(Long roleId);

	/**
	 * Insert.
	 *
	 * @param roleId       the role id
	 * @param actionIdList the action id list
	 */
	void insert(Long roleId, Set<Long> actionIdList);

	/**
	 * Delete by role id list int.
	 *
	 * @param roleIdList the role id list
	 *
	 * @return the int
	 */
	int deleteByRoleIdList(List<Long> roleIdList);
}
