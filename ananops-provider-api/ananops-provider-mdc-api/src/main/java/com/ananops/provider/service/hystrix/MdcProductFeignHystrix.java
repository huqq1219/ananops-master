/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MdcProductFeignHystrix.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.service.hystrix;

import com.ananops.provider.model.dto.ProductDto;
import com.ananops.provider.service.MdcProductFeignApi;
import com.ananops.wrapper.Wrapper;
import org.springframework.stereotype.Component;

/**
 * The class Mdc product feign hystrix.
 *
 * @author ananops.com@gmail.com
 */
@Component
public class MdcProductFeignHystrix implements MdcProductFeignApi {

	@Override
	public Wrapper<Integer> updateProductStockById(final ProductDto productDto) {
		return null;
	}

	@Override
	public Wrapper<String> getMainImage(final Long productId) {
		return null;
	}
}
