/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：UacLoginServiceImpl.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.service.impl;

import com.ananops.provider.model.domain.UacRole;
import com.ananops.provider.service.UacRoleService;
import com.google.common.base.Preconditions;
import com.ananops.PublicUtil;
import com.ananops.base.dto.LoginAuthDto;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.provider.model.constant.UacConstant;
import com.ananops.provider.model.domain.UacUser;
import com.ananops.provider.model.dto.user.LoginRespDto;
import com.ananops.provider.model.exceptions.UacBizException;
import com.ananops.provider.model.vo.MenuVo;
import com.ananops.provider.security.SecurityUtils;
import com.ananops.provider.service.UacLoginService;
import com.ananops.provider.service.UacMenuService;
import com.ananops.provider.service.UacUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * The class Uac login service.
 *
 * @author ananops.com@gmail.com
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UacLoginServiceImpl implements UacLoginService {

	@Resource
	private UacRoleService uacRoleService;
	@Resource
	private UacUserService uacUserService;
	@Resource
	private UacMenuService uacMenuService;

	@Override
	public LoginRespDto loginAfter(Long applicationId) {
		LoginRespDto loginRespDto = new LoginRespDto();
		String loginName = SecurityUtils.getCurrentLoginName();
		if (StringUtils.isEmpty(loginName)) {
			log.error("操作超时, 请重新登录 loginName={}", loginName);
			Preconditions.checkArgument(StringUtils.isNotEmpty(loginName), "操作超时, 请重新登录");
		}

		UacUser uacUser = uacUserService.findByLoginName(loginName);

		if (PublicUtil.isEmpty(uacUser)) {
			log.info("找不到用户信息 loginName={}", loginName);
			throw new UacBizException(ErrorCodeEnum.UAC10011002, loginName);
		}

		LoginAuthDto loginAuthDto = this.getLoginAuthDto(uacUser);
		List<MenuVo> menuVoList = uacMenuService.getMenuVoList(uacUser.getId(), applicationId);
		if (PublicUtil.isNotEmpty(menuVoList) && UacConstant.MENU_ROOT.equals(menuVoList.get(0).getMenuCode())) {
			menuVoList = menuVoList.get(0).getSubMenu();
		}

		if (PublicUtil.isNotEmpty(menuVoList)) {
			resetMenuVo(menuVoList);
		}
		List<UacRole> roleList = uacRoleService.findAllRoleInfoByUserId(uacUser.getId());
		loginRespDto.setRoleList(roleList);
		loginRespDto.setLoginAuthDto(loginAuthDto);
		loginRespDto.setMenuList(menuVoList);
		return loginRespDto;
	}

	private LoginAuthDto getLoginAuthDto(UacUser uacUser) {
		LoginAuthDto loginAuthDto = new LoginAuthDto();
		loginAuthDto.setUserId(uacUser.getId());
		loginAuthDto.setUserName(uacUser.getUserName());
		loginAuthDto.setLoginName(uacUser.getLoginName());
		loginAuthDto.setGroupId(uacUser.getGroupId());
		loginAuthDto.setGroupName(uacUser.getGroupName());
		return loginAuthDto;
	}

	private static void resetMenuVo(List<MenuVo> list){
		for (MenuVo menuVo : list) {
			List<MenuVo> subList= menuVo.getSubMenu();
			if (subList!=null) {
				if(subList.size()==0){
					menuVo.setSubMenu(null);
				}
				resetMenuVo(subList);
			}
		}
	}


}
