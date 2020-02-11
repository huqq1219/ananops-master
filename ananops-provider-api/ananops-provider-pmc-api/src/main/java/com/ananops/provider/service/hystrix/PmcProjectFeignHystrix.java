package com.ananops.provider.service.hystrix;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.provider.model.dto.PmcBatchProUser;
import com.ananops.provider.model.dto.PmcProjectDto;
import com.ananops.provider.model.dto.PmcProjectUserDto;
import com.ananops.provider.service.PmcProjectFeignApi;
import com.ananops.wrapper.Wrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created By ChengHao On 2019/12/20
 */
@Component
public class PmcProjectFeignHystrix implements PmcProjectFeignApi {

    @Override
    public Wrapper<PmcProjectDto> getProjectByProjectId(@PathVariable(value = "projectId") Long projectId) {
        return null;
    }

    @Override
    public Wrapper<List<PmcProjectDto>> getProjectListByGroupId(Long groupId) {
        return null;
    }

    @Override
    public Wrapper saveProject(PmcProjectDto pmcProjectDto) {
        return null;
    }

}
