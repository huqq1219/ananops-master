/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：UacRoleMainController.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.web.admin;

import com.ananops.base.dto.BaseQuery;
import com.ananops.provider.model.dto.user.UserInfoDto;
import com.ananops.provider.model.service.UacUserFeignApi;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ananops.base.dto.LoginAuthDto;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.core.annotation.LogAnnotation;
import com.ananops.core.annotation.ValidateAnnotation;
import com.ananops.core.support.BaseController;
import com.ananops.core.utils.RequestUtil;
import com.ananops.provider.model.domain.UacRole;
import com.ananops.provider.model.domain.UacRoleUser;
import com.ananops.provider.model.dto.base.ModifyStatusDto;
import com.ananops.provider.model.enums.UacRoleStatusEnum;
import com.ananops.provider.model.exceptions.UacBizException;
import com.ananops.provider.model.vo.RoleVo;
import com.ananops.provider.service.UacRoleService;
import com.ananops.provider.service.UacRoleUserService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 角色管理主页面.
 *
 * @author ananops.com @gmail.com
 */
@RestController
@RequestMapping(value = "/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Web - UacRoleMainController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UacRoleMainController extends BaseController {

	@Resource
	private UacRoleService uacRoleService;
	@Resource
	private UacRoleUserService uacRoleUserService;

	@Resource
	UacUserFeignApi uacUserFeignApi;

	/**
	 * 分页查询角色信息.
	 *
	 * @param role the role
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/queryRoleListWithPage")
	@ApiOperation(httpMethod = "POST", value = "查询角色列表")
	public Wrapper<PageInfo<RoleVo>> queryUacRoleListWithPage(@ApiParam(name = "role", value = "角色信息") @RequestBody UacRole role) {

		logger.info("查询角色列表roleQuery={}", role);
		PageHelper.startPage(role.getPageNum(), role.getPageSize());
		role.setOrderBy("created_time desc");
		List<RoleVo> roleVoList = uacRoleService.queryRoleListWithPage(role);
		return WrapMapper.ok(new PageInfo<>(roleVoList));
	}

	/**
	 * 查询用户可以绑定的角色列表
	 * @return
	 */
	@PostMapping(value = "/queryBindRoleWithPage")
	@ApiOperation(httpMethod = "POST", value = "查询用户可以绑定的角色列表")
	public Wrapper<List<UacRole>> queryBindRoleWithPage() {
		LoginAuthDto loginAuthDto = super.getLoginAuthDto();
		List<UacRole> uacRoleList = new ArrayList<>();
		if(loginAuthDto.getLoginName().equals("admin")){
			uacRoleList = uacRoleService.selectAll();
		}else{
			Wrapper<UserInfoDto> wrapper = uacUserFeignApi.getUacUserById(loginAuthDto.getUserId());
			uacRoleList = uacRoleService.queryBindRoleWithPage(wrapper.getResult().getRoleId());
		}
		return WrapMapper.ok(uacRoleList);
	}

	/**
	 * 删除角色信息.
	 *
	 * @param id the id
	 *
	 * @return the wrapper
	 */
	@LogAnnotation
	@PostMapping(value = "/deleteRoleById/{id}")
	@ApiOperation(httpMethod = "POST", value = "删除角色")
	public Wrapper deleteUacRoleById(@ApiParam(name = "id", value = "角色id") @PathVariable Long id) {
		int result = uacRoleService.deleteRoleById(id);
		return super.handleResult(result);
	}

	/**
	 * 批量删除角色.
	 *
	 * @param deleteIdList the delete id list
	 *
	 * @return the wrapper
	 */
	@LogAnnotation
	@PostMapping(value = "/batchDeleteByIdList")
	@ApiOperation(httpMethod = "POST", value = "批量删除角色")
	public Wrapper batchDeleteByIdList(@ApiParam(name = "deleteIdList", value = "角色Id") @RequestBody List<Long> deleteIdList) {
		logger.info("批量删除角色 idList={}", deleteIdList);
		uacRoleService.batchDeleteByIdList(deleteIdList);
		return WrapMapper.ok();
	}

	/**
	 * 修改角色状态.
	 *
	 * @param modifyStatusDto the modify status dto
	 *
	 * @return the wrapper
	 */
	@LogAnnotation
	@PostMapping(value = "/modifyRoleStatusById")
	@ApiOperation(httpMethod = "POST", value = "根据角色Id修改角色状态")
	public Wrapper modifyUacRoleStatusById(@ApiParam(name = "modifyRoleStatusDto", value = "修改角色状态数据") @RequestBody ModifyStatusDto modifyStatusDto) {
		logger.info("根据角色Id修改角色状态 modifyStatusDto={}", modifyStatusDto);
		Long roleId = modifyStatusDto.getId();
		if (roleId == null) {
			throw new UacBizException(ErrorCodeEnum.UAC10012001);
		}

		LoginAuthDto loginAuthDto = getLoginAuthDto();
		Long userId = loginAuthDto.getUserId();

		UacRoleUser ru = uacRoleUserService.getByUserIdAndRoleId(userId, roleId);

		if (ru != null && UacRoleStatusEnum.DISABLE.getType().equals(modifyStatusDto.getStatus())) {
			throw new UacBizException(ErrorCodeEnum.UAC10012002);
		}

		UacRole uacRole = new UacRole();
		uacRole.setId(roleId);
		uacRole.setStatus(modifyStatusDto.getStatus());
		uacRole.setUpdateInfo(loginAuthDto);

		int result = uacRoleService.update(uacRole);
		return super.handleResult(result);
	}


	/**
	 * 保存用户.
	 *
	 * @param role the role
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/save")
	@ApiOperation(httpMethod = "POST", value = "新增角色")
	@ValidateAnnotation
	@LogAnnotation
	public Wrapper save(@ApiParam(name = "role", value = "角色信息") @RequestBody UacRole role) {
		LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
		uacRoleService.saveRole(role, loginAuthDto);
		return WrapMapper.ok();
	}
}
