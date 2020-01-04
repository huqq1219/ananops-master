package com.ananops.provider.service;

import com.ananops.provider.service.hystrix.BillFeignHystrix;
import com.ananops.security.feign.OAuth2FeignAutoConfiguration;
import com.ananops.wrapper.Wrapper;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ananops-provider-bill", configuration = OAuth2FeignAutoConfiguration.class, fallback = BillFeignHystrix.class)
public interface BillFeignApi {
    @GetMapping(value = "/api/bill/getAmountByWorkOrderId/{workOrderId}")
    Wrapper<Float> getAmountByWorkOrderId(@RequestParam Long workOrderId);
}
