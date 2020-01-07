/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：UacActionMainController.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.web.admin;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.annotation.LogAnnotation;
import com.ananops.core.annotation.ValidateAnnotation;
import com.ananops.core.support.BaseController;
import com.ananops.core.utils.RequestUtil;
import com.ananops.provider.model.domain.UacAction;
import com.ananops.provider.model.dto.action.ActionMainQueryDto;
import com.ananops.provider.model.dto.base.ModifyStatusDto;
import com.ananops.provider.service.UacActionService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * The class Uac action main controller.
 *
 * @author ananops.com @gmail.com
 */
@RestController
@RequestMapping(value = "/action", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Web - UacActionMainController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UacActionMainController extends BaseController {

	@Resource
	private UacActionService uacActionService;

	/**
	 * 分页查询菜单的权限信息.
	 *
	 * @param action the action
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/queryListWithPage")
	@ApiOperation(httpMethod = "POST", value = "查询菜单的权限列表")
	public Wrapper queryUacActionListWithPage(@ApiParam(name = "action", value = "菜单权限信息") @RequestBody ActionMainQueryDto action) {

		logger.info("查询角色列表actionQuery={}", action);
		PageInfo pageInfo = uacActionService.queryActionListWithPage(action);
		return WrapMapper.ok(pageInfo);
	}

	/**
	 * 删除权限信息.
	 *
	 * @param id the id
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/deleteActionById/{id}")
	@ApiOperation(httpMethod = "POST", value = "删除权限")
	@LogAnnotation
	public Wrapper deleteUacActionById(@ApiParam(name = "id", value = "权限id") @PathVariable Long id) {
		int result = uacActionService.deleteActionById(id);
		return super.handleResult(result);
	}

	/**
	 * 批量删除权限.
	 *
	 * @param deleteIdList the delete id list
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/batchDeleteByIdList")
	@ApiOperation(httpMethod = "POST", value = "批量删除权限")
	@LogAnnotation
	public Wrapper batchDeleteByIdList(@ApiParam(name = "deleteIdList", value = "角色Id") @RequestBody List<Long> deleteIdList) {
		logger.info("批量删除角色 idList={}", deleteIdList);
		uacActionService.batchDeleteByIdList(deleteIdList);
		return WrapMapper.ok();
	}


	/**
	 * 保存权限信息.
	 *
	 * @param action the action
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/save")
	@ApiOperation(httpMethod = "POST", value = "新增权限")
	@ValidateAnnotation
	@LogAnnotation
	public Wrapper save(@ApiParam(name = "action", value = "权限信息") @RequestBody UacAction action) {
		LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
		uacActionService.saveAction(action, loginAuthDto);
		return WrapMapper.ok();
	}

	/**
	 * 根据权限Id修改权限状态.
	 *
	 * @param modifyStatusDto the modify status dto
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/modifyStatus")
	@ApiOperation(httpMethod = "POST", value = "根据权限Id修改权限状态")
	@LogAnnotation
	public Wrapper modifyActionStatus(@ApiParam(name = "modifyActionStatus", value = "修改权限状态") @RequestBody ModifyStatusDto modifyStatusDto) {
		logger.info("根据角色Id修改权限状态 modifyStatusDto={}", modifyStatusDto);
		Long actionId = modifyStatusDto.getId();
		Preconditions.checkArgument(actionId != null, "权限ID不能为空");

		UacAction uacRole = new UacAction();
		uacRole.setId(actionId);
		uacRole.setStatus(modifyStatusDto.getStatus());
		uacRole.setUpdateInfo(getLoginAuthDto());

		int result = uacActionService.update(uacRole);
		return super.handleResult(result);
	}
}
