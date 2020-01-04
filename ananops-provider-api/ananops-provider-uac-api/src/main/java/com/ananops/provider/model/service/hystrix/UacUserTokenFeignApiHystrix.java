/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：UacUserTokenFeignApiHystrix.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.service.hystrix;


import com.ananops.provider.model.service.UacUserTokenFeignApi;
import com.ananops.wrapper.Wrapper;
import org.springframework.stereotype.Component;

/**
 * The class Uac user token feign api hystrix.
 *
 * @author ananops.com @gmail.com
 */
@Component
public class UacUserTokenFeignApiHystrix implements UacUserTokenFeignApi {

	@Override
	public Wrapper<Integer> updateTokenOffLine() {
		return null;
	}
}
