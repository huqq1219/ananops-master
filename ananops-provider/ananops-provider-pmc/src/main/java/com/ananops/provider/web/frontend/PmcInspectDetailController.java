package com.ananops.provider.web.frontend;

import com.ananops.core.support.BaseController;
import com.ananops.provider.model.domain.PmcInspectDetail;
import com.ananops.provider.model.dto.PmcInspectDetailDto;
import com.ananops.provider.service.PmcInspectDetailsService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created By ChengHao On 2019/12/23
 */
@RestController
@RequestMapping(value = "/inspectDetail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB -PmcInspectDetailController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PmcInspectDetailController extends BaseController {
    @Autowired
    PmcInspectDetailsService pmcInspectDetailService;

    @PostMapping("/save")
    @ApiOperation(httpMethod = "POST", value = "编辑巡检详情,当id为空时新增巡检任务,不为空时为更新巡检任务")
    public Wrapper save(@RequestBody PmcInspectDetailDto pmcInspectDetailDto) {
        PmcInspectDetail pmcInspectDetail = new PmcInspectDetail();
        BeanUtils.copyProperties(pmcInspectDetailDto, pmcInspectDetail);
        int result = pmcInspectDetailService.saveInspectDetail(pmcInspectDetail);
        return WrapMapper.ok();
    }

    @PostMapping("/getInspectDetailById/{id}")
    @ApiOperation(httpMethod = "POST", value = "根据id获取巡检详情")
    public Wrapper<PmcInspectDetail> getInspectDetailById(@PathVariable Long id) {
        PmcInspectDetail pmcInspectDetail = pmcInspectDetailService.getInspectDetailById(id);
        return WrapMapper.ok(pmcInspectDetail);
    }

    @PostMapping("/getInspectDetailList/{inspectTaskId}")
    @ApiOperation(httpMethod = "POST", value = "获取巡检任务详情")
    public Wrapper<List<PmcInspectDetail>> getInspectDetailList(@PathVariable Long inspectTaskId) {
        List<PmcInspectDetail> pmcInspectDetailList = pmcInspectDetailService.getInspectDetailList(inspectTaskId);
        return WrapMapper.ok(pmcInspectDetailList);
    }

    @PostMapping("deleteDetailById/{id}")
    @ApiOperation(httpMethod = "POST", value = "删除巡检详情")
    public Wrapper deleteDetailById(@PathVariable Long id) {
        int result = pmcInspectDetailService.deleteDetailById(id);
        return WrapMapper.ok();
    }

    @PostMapping("deleteDetailByTaskId/{taskId}")
    @ApiOperation(httpMethod = "POST", value = "删除巡检详情")
    public Wrapper deleteDetailByTaskId(@ApiParam(value = "巡检任务id") @PathVariable Long taskId) {
        int result = pmcInspectDetailService.deleteDetailByTaskId(taskId);
        return WrapMapper.ok();
    }

}
