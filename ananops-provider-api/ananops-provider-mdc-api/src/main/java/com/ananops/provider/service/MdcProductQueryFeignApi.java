
package com.ananops.provider.service;

import com.ananops.annotation.NoNeedAccessAuthentication;
import com.ananops.provider.model.dto.ProductDto;
import com.ananops.provider.model.vo.ProductDetailVo;
import com.ananops.provider.service.hystrix.MdcProductQueryFeignHystrix;
import com.ananops.security.feign.OAuth2FeignAutoConfiguration;
import com.ananops.wrapper.Wrapper;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * The interface Mdc product query feign api.
 *
 * @author ananops.com @gmail.com
 */
@FeignClient(value = "ananops-provider-mdc", configuration = OAuth2FeignAutoConfiguration.class, fallback = MdcProductQueryFeignHystrix.class)
public interface MdcProductQueryFeignApi {

	/**
	 * 查询商品详情信息.
	 *
	 * @param productId the product id
	 *
	 * @return the product detail
	 */
	@PostMapping(value = "/api/product/getProductDetail/{productId}")
	@NoNeedAccessAuthentication
	Wrapper<ProductDetailVo> getProductDetail(@PathVariable("productId") Long productId);

	/**
	 * Select by id wrapper.
	 *
	 * @param productId the product id
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/api/product/selectById/{productId}")
	@NoNeedAccessAuthentication
	Wrapper<ProductDto> selectById(@PathVariable("productId") Long productId);
}
