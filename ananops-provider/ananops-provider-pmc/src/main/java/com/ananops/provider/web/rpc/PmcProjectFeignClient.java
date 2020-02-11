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
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    @ApiOperation(httpMethod = "POST", value = "根据项目Id查询项目信息")
    public Wrapper<PmcProjectDto> getProjectByProjectId(@PathVariable(value = "projectId") Long projectId) {
        logger.info("getProjectByProjectId - 根据项目Id查询项目信息. projectId={}", projectId);
        PmcProject pmcProject = pmcProjectService.getProjectById(projectId);
        PmcProjectDto pmcProjectDto = new PmcProjectDto();
        BeanUtils.copyProperties(pmcProject, pmcProjectDto);
        logger.info("getProjectByProjectId - 根据项目Id查询项目信息. [OK] pmcProjectDto={}", pmcProjectDto);
        return WrapMapper.ok(pmcProjectDto);
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "根据组织id查询项目信息")
    public Wrapper<List<PmcProjectDto>> getProjectListByGroupId(@PathVariable(value = "groupId") Long groupId) {
        List<PmcProject> pmcProjectList = pmcProjectService.getProjectListByGroupId(groupId);
        List<PmcProjectDto> pmcProjectDtoList = new ArrayList<>();
        BeanUtils.copyProperties(pmcProjectList, pmcProjectDtoList);
        return WrapMapper.ok(pmcProjectDtoList);
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "保存项目")
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
