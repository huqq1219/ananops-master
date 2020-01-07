/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MdcProductCategoryQueryFeignApi.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.service;

import com.github.pagehelper.PageInfo;
import com.ananops.annotation.NoNeedAccessAuthentication;
import com.ananops.provider.model.dto.ProductCategoryDto;
import com.ananops.provider.model.dto.ProductReqDto;
import com.ananops.provider.service.hystrix.MdcProductCategoryQueryFeignHystrix;
import com.ananops.security.feign.OAuth2FeignAutoConfiguration;
import com.ananops.wrapper.Wrapper;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * The interface Mdc product category query feign api.
 *
 * @author ananops.com @gmail.com
 */
@FeignClient(value = "ananops-provider-mdc", configuration = OAuth2FeignAutoConfiguration.class, fallback = MdcProductCategoryQueryFeignHystrix.class)
public interface MdcProductCategoryQueryFeignApi {

	/**
	 * 查询分类信息.
	 *
	 * @param pid the pid
	 *
	 * @return the product category data
	 */
	@PostMapping(value = "/api/productCategory/getProductCategoryDtoByPid/{pid}")
	@NoNeedAccessAuthentication
	Wrapper<List<ProductCategoryDto>> getProductCategoryData(@PathVariable("pid") Long pid);

	/**
	 * 查询商品列表.
	 *
	 * @param productReqDto the product req dto
	 *
	 * @return the product list
	 */
	@PostMapping(value = "/api/product/getProductList")
	@NoNeedAccessAuthentication
	Wrapper<PageInfo> getProductList(@RequestBody ProductReqDto productReqDto);

}
