package com.ananops.provider.web.rpc;

import com.ananops.core.support.BaseController;
import com.ananops.provider.model.domain.PmcProjectUser;
import com.ananops.provider.model.dto.PmcBatchProUser;
import com.ananops.provider.model.dto.PmcProjectUserDto;
import com.ananops.provider.service.PmcProjectEngineerFeignApi;
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
 * Created By ChengHao On 2019/12/30
 */
@RefreshScope
@RestController
@Api(value = "API -  PmcProjectEngineerFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PmcProjectEngineerFeignClient extends BaseController implements PmcProjectEngineerFeignApi {
    @Resource
    private PmcProjectService pmcProjectService;

    @Override
    @ApiOperation(httpMethod = "POST", value = "批量插入项目工程师关系")
    public Wrapper saveProUserList(@RequestBody PmcBatchProUser pmcBatchProUser) {
        int result = 0;
        List<PmcProjectUserDto> pmcProjectUserDtoList = pmcBatchProUser.getPmcProjectUserDtoList();
        List<PmcProjectUser> pmcProjectUserList = new ArrayList<PmcProjectUser>();
        BeanUtils.copyProperties(pmcProjectUserDtoList, pmcProjectUserList);
        for (PmcProjectUser pmcProjectUser : pmcProjectUserList) {
            int count = pmcProjectService.addProUser(pmcProjectUser);
            result += count;
        }
        return WrapMapper.ok(result);
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "根据项目id获取工程师id列表")
    public Wrapper<List<Long>> getEngineersIdByProjectId(@PathVariable(value = "projectId") Long projectId) {
        List<Long> engineersId = pmcProjectService.getEngineersIdByProjectId(projectId);
        return WrapMapper.ok(engineersId);
    }
}
