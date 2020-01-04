/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：UacUserTokenService.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.service;

import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.github.pagehelper.PageInfo;
import com.ananops.base.dto.LoginAuthDto;
import com.ananops.base.dto.UserTokenDto;
import com.ananops.core.support.IService;
import com.ananops.provider.model.domain.UacUserToken;
import com.ananops.provider.model.dto.token.TokenMainQueryDto;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录jwt token 管理.
 *
 * @author ananops.com @gmail.com
 */
public interface UacUserTokenService extends IService<UacUserToken> {
	/**
	 * 保存token.
	 *
	 * @param accessToken  the access token
	 * @param refreshToken the refresh token
	 * @param loginAuthDto the login auth dto
	 * @param request      the request
	 */
	void saveUserToken(String accessToken, String refreshToken, LoginAuthDto loginAuthDto, HttpServletRequest request);

	/**
	 * 获取token.
	 *
	 * @param accessToken the access token
	 *
	 * @return the by access token
	 */
	UserTokenDto getByAccessToken(String accessToken);


	/**
	 * 更新token.
	 *
	 * @param tokenDto     the token dto
	 * @param loginAuthDto the login auth dto
	 */
	void updateUacUserToken(UserTokenDto tokenDto, LoginAuthDto loginAuthDto);

	/**
	 * 分页查询token列表.
	 *
	 * @param token the token
	 *
	 * @return the page info
	 */
	PageInfo listTokenWithPage(TokenMainQueryDto token);

	/**
	 * 刷新token.
	 *
	 * @param accessToken  the access token
	 * @param refreshToken the refresh token
	 * @param request      the request
	 *
	 * @return the string
	 *
	 * @throws HttpProcessException the http process exception
	 */
	String refreshToken(String accessToken, String refreshToken, HttpServletRequest request) throws HttpProcessException;

	/**
	 * 更新token离线状态.
	 *
	 * @return the int
	 */
	int batchUpdateTokenOffLine();

}
