/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MdcAddressQueryFeignHystrix.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.service.hystrix;

import com.ananops.provider.model.dto.AddressDTO;
import com.ananops.provider.service.MdcAddressQueryFeignApi;
import com.ananops.wrapper.Wrapper;
import org.springframework.stereotype.Component;

/**
 * The class Mdc product query feign hystrix.
 *
 * @author ananops.com@gmail.com
 */
@Component
public class MdcAddressQueryFeignHystrix implements MdcAddressQueryFeignApi {

	@Override
	public Wrapper<AddressDTO> getById(final Long addressId) {
		return null;
	}
}
