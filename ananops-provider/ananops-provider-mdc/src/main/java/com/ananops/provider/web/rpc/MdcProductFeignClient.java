/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MdcProductFeignClient.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.web.rpc;

import com.google.common.base.Preconditions;
import com.ananops.PubUtils;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.core.support.BaseController;
import com.ananops.provider.model.dto.ProductDto;
import com.ananops.provider.service.MdcProductFeignApi;
import com.ananops.provider.service.MdcProductService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * The class Mdc product feign client.
 *
 * @author ananops.com@gmail.com
 */
@RefreshScope
@RestController
@Api(value = "API - UacUserQueryFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ApiIgnore
public class MdcProductFeignClient extends BaseController implements MdcProductFeignApi {
	@Resource
	private MdcProductService mdcProductService;

	@Override
	@ApiOperation(httpMethod = "POST", value = "更新商品库存")
	public Wrapper<Integer> updateProductStockById(@RequestBody ProductDto productDto) {
		logger.info("更新商品库存. productDto={}", productDto);
		Preconditions.checkArgument(!PubUtils.isNull(productDto, productDto.getId()), ErrorCodeEnum.MDC10021021.msg());
		int result = mdcProductService.updateProductStockById(productDto);
		return WrapMapper.ok(result);
	}

	@Override
	public Wrapper<String> getMainImage(@RequestParam("productId") Long productId) {
		String mainImage = mdcProductService.getMainImage(productId);
		return WrapMapper.ok(mainImage);
	}
}
