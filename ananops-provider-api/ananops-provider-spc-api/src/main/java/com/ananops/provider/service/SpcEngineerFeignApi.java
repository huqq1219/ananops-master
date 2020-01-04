package com.ananops.provider.service;

import com.ananops.provider.model.dto.EngineerDto;
import com.ananops.provider.service.hystrix.SpcEngineerFeignHystrix;
import com.ananops.security.feign.OAuth2FeignAutoConfiguration;
import com.ananops.wrapper.Wrapper;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 提供模块间对工程师的操作Feign API
 *
 * Created by bingyueduan on 2019/12/30.
 */
@FeignClient(value = "ananops-provider-spc", configuration = OAuth2FeignAutoConfiguration.class, fallback = SpcEngineerFeignHystrix.class)
public interface SpcEngineerFeignApi {

    @PostMapping(value = "/api/engineer/getEngineersByProjectId/{projectId}")
    Wrapper<List<EngineerDto>> getEngineersByProjectId(@PathVariable(value = "projectId") Long projectId);

    @PostMapping(value = "/api/engineer/getEngineersById/{engineerId}")
    Wrapper<EngineerDto> getEngineerById(@PathVariable(value = "engineerId") Long engineerId);

    @PostMapping(value = "/api/engineer/getEngineersByBatchId")
    Wrapper<List<EngineerDto>> getEngineersByBatchId(@RequestParam("engineerIdList") List<Long> engineerIdList);
}
