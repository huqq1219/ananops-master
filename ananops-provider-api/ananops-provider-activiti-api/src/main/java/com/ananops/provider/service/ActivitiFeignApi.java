package com.ananops.provider.service;

import com.ananops.provider.service.dto.ApproSubmitDto;
import com.ananops.provider.service.hystrix.ActivitiFeignHystrix;
import com.ananops.security.feign.OAuth2FeignAutoConfiguration;
import com.ananops.wrapper.Wrapper;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "ananops-provider-activiti",configuration = OAuth2FeignAutoConfiguration.class,fallback = ActivitiFeignHystrix.class)
public interface ActivitiFeignApi {

    @PostMapping(value = "/api/activiti/submit")
    Wrapper<String> submit(@RequestBody ApproSubmitDto approSubmitDto);
}
