/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MdcProductCategoryFeignApi.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.service;

import com.ananops.provider.service.hystrix.MdcProductCategoryFeignHystrix;
import com.ananops.security.feign.OAuth2FeignAutoConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * The interface Mdc product category feign api.
 *
 * @author ananops.com@gmail.com
 */
@FeignClient(value = "ananops-provider-mdc", configuration = OAuth2FeignAutoConfiguration.class, fallback = MdcProductCategoryFeignHystrix.class)
public interface MdcProductCategoryFeignApi {

}
