/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MdcProductCategoryQueryFeignHystrix.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.service.hystrix;

import com.github.pagehelper.PageInfo;
import com.ananops.provider.model.dto.ProductCategoryDto;
import com.ananops.provider.model.dto.ProductReqDto;
import com.ananops.provider.service.MdcProductCategoryQueryFeignApi;
import com.ananops.wrapper.Wrapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The class Mdc product category query feign hystrix.
 *
 * @author ananops.com@gmail.com
 */
@Component
public class MdcProductCategoryQueryFeignHystrix implements MdcProductCategoryQueryFeignApi {
	@Override
	public Wrapper<List<ProductCategoryDto>> getProductCategoryData(final Long pid) {
		return null;
	}

	@Override
	public Wrapper<PageInfo> getProductList(final ProductReqDto productReqDto) {
		return null;
	}
}
