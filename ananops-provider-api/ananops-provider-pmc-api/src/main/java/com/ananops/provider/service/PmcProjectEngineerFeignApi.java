package com.ananops.provider.service;

import com.ananops.provider.model.dto.PmcBatchProUser;
import com.ananops.provider.service.hystrix.PmcProjectEngineerFeignHystrix;
import com.ananops.provider.service.hystrix.PmcProjectFeignHystrix;
import com.ananops.security.feign.OAuth2FeignAutoConfiguration;
import com.ananops.wrapper.Wrapper;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created By ChengHao On 2019/12/30
 */
@FeignClient(value = "ananops-provider-pmc",configuration = OAuth2FeignAutoConfiguration.class,fallback = PmcProjectEngineerFeignHystrix.class)
public interface PmcProjectEngineerFeignApi {
    /**
     * 批量插入项目工程师关系
     * @param pmcBatchProUser
     * @return
     */
    @PostMapping("/api/projectEngineer/saveProUserList")
    Wrapper saveProUserList(@RequestBody PmcBatchProUser pmcBatchProUser);


    /**
     * 根据项目id获取工程师id列表
     * @param projectId
     * @return
     */
    @PostMapping("/api/projectEngineer/getEngineersIdByProjectId/{projectId}")
    Wrapper<List<Long>> getEngineersIdByProjectId(@PathVariable(value = "projectId") Long projectId);
}
