/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：UacGrantAuth4RoleController.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.web.admin;

import com.ananops.core.annotation.LogAnnotation;
import com.ananops.core.support.BaseController;
import com.ananops.provider.model.dto.role.RoleBindActionDto;
import com.ananops.provider.model.dto.role.RoleBindMenuDto;
import com.ananops.provider.service.UacRoleService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 角色授权.
 *
 * @author ananops.com @gmail.com
 */
@RestController
@RequestMapping(value = "/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Web - UacGrantAuth4RoleController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UacGrantAuth4RoleController extends BaseController {

	@Resource
	private UacRoleService uacRoleService;

	/**
	 * 角色分配权限.
	 *
	 * @param roleBindActionDto the role bind action dto
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/bindAction")
	@ApiOperation(httpMethod = "POST", value = "角色分配权限")
	@LogAnnotation
	public Wrapper bindAction(@ApiParam(name = "bindAuth", value = "权限信息") @RequestBody RoleBindActionDto roleBindActionDto) {
		logger.info("角色分配权限. roleBindActionDto= {}", roleBindActionDto);
		uacRoleService.bindAction(roleBindActionDto);
		return WrapMapper.ok();
	}

	/**
	 * 角色分配权限.
	 *
	 * @param roleBindMenuDto the role bind menu dto
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/bindMenu")
	@ApiOperation(httpMethod = "POST", value = "角色分配权限")
	@LogAnnotation
	public Wrapper bindMenu(@ApiParam(name = "bindAuth", value = "权限信息") @RequestBody RoleBindMenuDto roleBindMenuDto) {
		logger.info("角色分配权限. roleBindMenuDto= {}", roleBindMenuDto);
		uacRoleService.bindMenu(roleBindMenuDto);
		return WrapMapper.ok();
	}
}
