/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：UacRoleMenuMapper.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.mapper;

import com.ananops.core.mybatis.MyMapper;
import com.ananops.provider.model.domain.UacRoleMenu;
import com.ananops.provider.model.vo.role.MenuCountVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The interface Uac role menu mapper.
 *
 * @author ananops.com @gmail.com
 */
@Mapper
@Component
public interface UacRoleMenuMapper extends MyMapper<UacRoleMenu> {
	/**
	 * Count child menu num list.
	 *
	 * @param roleId the role id
	 *
	 * @return the list
	 */
	List<MenuCountVo> countChildMenuNum(Long roleId);

	/**
	 * Add role menu list int.
	 *
	 * @param addUacRoleMenuList the add uac role menu list
	 *
	 * @return the int
	 */
	int addRoleMenuList(List<UacRoleMenu> addUacRoleMenuList);


	/**
	 * Delete by role id list int.
	 *
	 * @param roleIdList the role id list
	 *
	 * @return the int
	 */
	int deleteByRoleIdList(@Param("roleIdList") List<Long> roleIdList);
}