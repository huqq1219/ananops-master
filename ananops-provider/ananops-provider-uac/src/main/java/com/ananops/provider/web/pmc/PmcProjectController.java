package com.ananops.provider.web.pmc;

import com.ananops.core.support.BaseController;
import com.ananops.provider.model.dto.PmcProjectDto;
import com.ananops.provider.service.PmcProjectFeignApi;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created By ChengHao On 2019/12/20
 */
@RestController
@RequestMapping(value = "/project", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - PmcProjectController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PmcProjectController extends BaseController {
    @Resource
    private PmcProjectFeignApi pmcProjectFeignApi;

    @PostMapping("/save")
    @ApiOperation(httpMethod = "POST", value = "编辑项目,当id为空时新增项目,不为空时为更新项目信息")
    public Wrapper saveProject(@RequestBody PmcProjectDto pmcProjectDto){
        return pmcProjectFeignApi.saveProject(pmcProjectDto);
    }


}

