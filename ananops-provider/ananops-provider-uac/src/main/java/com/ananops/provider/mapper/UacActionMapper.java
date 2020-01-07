/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：UacActionMapper.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.mapper;

import com.ananops.core.mybatis.MyMapper;
import com.ananops.provider.model.domain.UacAction;
import com.ananops.provider.model.domain.UacMenu;
import com.ananops.provider.model.dto.user.Perm;
import com.ananops.provider.model.vo.ActionVo;
import com.ananops.provider.model.vo.MenuVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The interface Uac action mapper.
 *
 * @author ananops.com@gmail.com
 */
@Mapper
@Component
public interface UacActionMapper extends MyMapper<UacAction> {
	/**
	 * Find all perms list.
	 *
	 * @return the list
	 */
	List<Perm> findAllPerms();

	/**
	 * Find action code list by user id list.
	 *
	 * @param userId the user id
	 *
	 * @return the list
	 */
	List<String> findActionCodeListByUserId(Long userId);

	/**
	 * Batch delete by id list int.
	 *
	 * @param deleteIdList the delete id list
	 *
	 * @return the int
	 */
	int batchDeleteByIdList(@Param("idList") List<Long> deleteIdList);

	/**
	 * Query action list with page list.
	 *
	 * @param uacAction the uac action
	 *
	 * @return the list
	 */
	List<ActionVo> queryActionListWithPage(UacAction uacAction);

	/**
	 * Delete by menu id int.
	 *
	 * @param id the id
	 *
	 * @return the int
	 */
	int deleteByMenuId(@Param("menuId") Long id);

	/**
	 * Gets checked action list.
	 *
	 * @param roleId the role id
	 *
	 * @return the checked action list
	 */
	List<Long> getCheckedActionList(@Param("roleId") Long roleId);

	/**
	 * Gets own auth list.
	 *
	 * @param userId the user id
	 *
	 * @return the own auth list
	 */
	List<MenuVo> getOwnAuthList(@Param("userId") Long userId);

	/**
	 * Gets checked menu list.
	 *
	 * @param roleId the role id
	 *
	 * @return the checked menu list
	 */
	List<Long> getCheckedMenuList(@Param("roleId") Long roleId);

	/**
	 * Gets own uac action list by user id.
	 *
	 * @param userId the user id
	 *
	 * @return the own uac action list by user id
	 */
	List<UacAction> getOwnUacActionListByUserId(Long userId);

	/**
	 * 根据角色ID查询权限列表.
	 *
	 * @param roleId the role id
	 *
	 * @return the list
	 */
	List<UacAction> listActionListByRoleId(@Param("roleId") Long roleId);

	/**
	 * List action list list.
	 *
	 * @param menuList the menu list
	 *
	 * @return the list
	 */
	List<UacAction> listActionList(@Param("menuList") List<UacMenu> menuList);
}