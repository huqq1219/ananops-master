/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MdcProductQueryFeignClient.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.web.rpc;

import com.ananops.PublicUtil;
import com.ananops.core.support.BaseController;
import com.ananops.provider.model.domain.MdcProduct;
import com.ananops.provider.model.dto.ProductDto;
import com.ananops.provider.model.vo.ProductDetailVo;
import com.ananops.provider.service.MdcProductQueryFeignApi;
import com.ananops.provider.service.MdcProductService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * The class Mdc product query feign client.
 *
 * @author ananops.com@gmail.com
 */
@RefreshScope
@RestController
@Api(value = "API - MdcProductQueryFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ApiIgnore
public class MdcProductQueryFeignClient extends BaseController implements MdcProductQueryFeignApi {

	@Resource
	private MdcProductService mdcProductService;

	@Override
	@ApiOperation(httpMethod = "POST", value = "根据商品ID查询商品详细信息")
	public Wrapper<ProductDetailVo> getProductDetail(@PathVariable("productId") Long productId) {
		logger.info("根据商品ID查询商品详细信息. productId={}", productId);
		ProductDetailVo productDto = mdcProductService.getProductDetail(productId);
		return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, productDto);
	}

	@Override
	@ApiOperation(httpMethod = "POST", value = "根据商品ID查询商品信息")
	public Wrapper<ProductDto> selectById(@PathVariable("productId") Long productId) {
		logger.info("根据商品ID查询商品信息. productId={}", productId);
		ProductDto productDto = null;
		MdcProduct mdcProduct = mdcProductService.selectByKey(productId);
		if (PublicUtil.isNotEmpty(mdcProduct)) {
			productDto = new ProductDto();
			BeanUtils.copyProperties(mdcProduct, productDto);
		}
		return WrapMapper.ok(productDto);
	}
}
