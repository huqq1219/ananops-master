package com.ananops.provider.service;

import com.ananops.provider.model.dto.PmcBatchProUser;
import com.ananops.provider.model.dto.PmcProjectDto;
import com.ananops.provider.model.dto.PmcProjectUserDto;
import com.ananops.provider.service.hystrix.PmcProjectFeignHystrix;
import com.ananops.security.feign.OAuth2FeignAutoConfiguration;
import com.ananops.wrapper.Wrapper;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * Created By ChengHao On 2019/12/20
 */
@FeignClient(value = "ananops-provider-pmc",configuration = OAuth2FeignAutoConfiguration.class,fallback = PmcProjectFeignHystrix.class)
public interface PmcProjectFeignApi {


    /**
     * 根据项目id获取项目信息
     * @param projectId
     * @return
     */
    @PostMapping("/api/project/getProjectById/{projectId}")
    Wrapper<PmcProjectDto> getProjectByProjectId(@PathVariable(value = "projectId") Long projectId);

    /**
     * 编辑项目信息
     * @param pmcProjectDto
     * @return
     */
    @PostMapping("/api/project/save")
    Wrapper saveProject(@RequestBody PmcProjectDto pmcProjectDto);


}
