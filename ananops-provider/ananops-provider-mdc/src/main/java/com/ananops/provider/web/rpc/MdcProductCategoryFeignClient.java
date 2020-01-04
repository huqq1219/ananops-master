/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MdcProductCategoryFeignClient.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.web.rpc;

import com.ananops.core.support.BaseController;
import com.ananops.provider.service.MdcProductCategoryFeignApi;
import io.swagger.annotations.Api;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

/**
 * The class Mdc product category feign client.
 *
 * @author ananops.com@gmail.com
 */
@RefreshScope
@RestController
@Api(value = "API - MdcProductCategoryFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MdcProductCategoryFeignClient extends BaseController implements MdcProductCategoryFeignApi {
}
