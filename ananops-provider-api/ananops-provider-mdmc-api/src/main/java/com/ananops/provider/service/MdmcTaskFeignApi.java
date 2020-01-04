package com.ananops.provider.service;

import com.ananops.provider.service.hystrix.MdmcTaskFeignHystrix;
import com.ananops.security.feign.OAuth2FeignAutoConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient( value = "ananops-provider-mdmc", configuration = OAuth2FeignAutoConfiguration.class, fallback = MdmcTaskFeignHystrix.class)
public interface MdmcTaskFeignApi {

}
