package com.ananops.provider.service;

import com.ananops.provider.model.dto.PmcProjectDto;
import com.ananops.provider.service.hystrix.PmcProjectFeignHystrix;
import com.ananops.security.feign.OAuth2FeignAutoConfiguration;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


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
     * 获取某个组织的项目列表
     * @param groupId
     * @return
     */
    @PostMapping("/api/getProjectListByGroupId/{groupId}")
    Wrapper<List<PmcProjectDto>> getProjectListByGroupId(@PathVariable(value = "groupId") Long groupId);


    /**
     * 编辑项目信息
     * @param pmcProjectDto
     * @return
     */
    @PostMapping("/api/project/save")
    Wrapper saveProject(@RequestBody PmcProjectDto pmcProjectDto);



}
