package com.ananops.provider.web.rpc;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.BaseController;
import com.ananops.provider.model.domain.PmcProject;
import com.ananops.provider.model.dto.PmcProjectDto;
import com.ananops.provider.service.PmcProjectFeignApi;
import com.ananops.provider.service.PmcProjectService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created By ChengHao On 2019/12/20
 */
@RefreshScope
@RestController
@Api(value = "API - PmcProjectFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PmcProjectFeignClient extends BaseController implements PmcProjectFeignApi {
    @Resource
    private PmcProjectService pmcProjectService;

    @Override
    public Wrapper<PmcProjectDto> getProjectByProjectId(Long projectId) {
        PmcProject pmcProject = pmcProjectService.getProjectById(projectId);
        PmcProjectDto pmcProjectDto = new PmcProjectDto();
        BeanUtils.copyProperties(pmcProject, pmcProjectDto);
        return WrapMapper.ok(pmcProjectDto);
    }

    @Override
    public Wrapper saveProject(@RequestBody PmcProjectDto pmcProjectDto) {
//        LoginAuthDto loginAuthDto = getLoginAuthDto();
        LoginAuthDto loginAuthDto = new LoginAuthDto();
        loginAuthDto.setUserId((long) 1);
        loginAuthDto.setUserName("jajja");
        PmcProject pmcProject = new PmcProject();
        BeanUtils.copyProperties(pmcProjectDto, pmcProject);
        int result = pmcProjectService.saveProject(pmcProject, loginAuthDto);
        return handleResult(result);
    }

}
