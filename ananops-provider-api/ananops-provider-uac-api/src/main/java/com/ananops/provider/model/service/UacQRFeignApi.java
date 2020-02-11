package com.ananops.provider.model.service;

import com.ananops.provider.model.service.hystrix.UacQRFeignHystrix;
import com.ananops.security.feign.OAuth2FeignAutoConfiguration;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FeignClient(value = "ananops-provider-uac", configuration = OAuth2FeignAutoConfiguration.class, fallback = UacQRFeignHystrix.class)
public interface UacQRFeignApi {

    @GetMapping("/api/uac/user/getQrCode/{content}")
    void getQrCode(HttpServletRequest request, HttpServletResponse response, @ApiParam(value = "content") @PathVariable(name = "content") String content) throws Exception;
}
