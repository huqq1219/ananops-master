/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：UacUserTokenFeignApi.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.service;


import com.ananops.provider.model.service.hystrix.UacUserTokenFeignApiHystrix;
import com.ananops.security.feign.OAuth2FeignAutoConfiguration;
import com.ananops.wrapper.Wrapper;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * The interface Uac user token feign api.
 *
 * @author ananops.com @gmail.com
 */
@FeignClient(value = "ananops-provider-uac", configuration = OAuth2FeignAutoConfiguration.class, fallback = UacUserTokenFeignApiHystrix.class)
public interface UacUserTokenFeignApi {

	/**
	 * 超时token更新为离线.
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/api/uac/token/updateTokenOffLine")
	Wrapper<Integer> updateTokenOffLine();
}
