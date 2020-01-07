package com.ananops.provider.web.rpc;

import com.ananops.core.support.BaseController;
import com.ananops.provider.model.dto.EngineerDto;
import com.ananops.provider.service.SpcEngineerFeignApi;
import com.ananops.provider.service.SpcEngineerService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 加盟服务商提供的对Engineer操作的Feign客户端
 *
 * Created by bingyueduan on 2019/12/30.
 */
@RefreshScope
@RestController
@Api(value = "API - SpcEngineerFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SpcEngineerFeignClient extends BaseController implements SpcEngineerFeignApi {

    @Resource
    private SpcEngineerService spcEngineerService;

    @Override
    @ApiOperation(httpMethod = "POST", value = "根据项目Id获取工程师列表")
    public Wrapper<List<EngineerDto>> getEngineersByProjectId(@PathVariable(value = "projectId") Long projectId) {
        logger.info("根据项目Id查询工程师列表. projectId={}", projectId);
        return WrapMapper.ok(spcEngineerService.getEngineersByProjectId(projectId));
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "根据工程师Id获取工程师信息")
    public Wrapper<EngineerDto> getEngineerById(@PathVariable(value = "engineerId") Long engineerId) {
        logger.info("根据工程师Id查询工程师信息. engineerId={}", engineerId);
        return WrapMapper.ok(spcEngineerService.getEngineerById(engineerId));
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "根据工程师Id集合批量获取工程师信息")
    public Wrapper<List<EngineerDto>> getEngineersByBatchId(@RequestParam("engineerIdList") List<Long> engineerIdList) {
        logger.info("根据工程师Id集合批量获取工程师信息. engineerIdList={}", engineerIdList);
        return WrapMapper.ok(spcEngineerService.getEngineersByBatchId(engineerIdList));
    }
}
