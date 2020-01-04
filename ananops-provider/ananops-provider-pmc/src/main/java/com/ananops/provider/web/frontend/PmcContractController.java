package com.ananops.provider.web.frontend;

import com.ananops.base.dto.BaseQuery;
import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.BaseController;
import com.ananops.provider.model.domain.PmcContract;
import com.ananops.provider.model.dto.PmcContractDto;
import com.ananops.provider.service.PmcContractService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created By ChengHao On 2019/11/28
 */
@RestController
@RequestMapping(value ="/contract",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
@Api(value = "WEB -PmcContactController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PmcContractController extends BaseController {
    @Autowired
    PmcContractService pmcContractService;

    @PostMapping("/save")
    @ApiOperation(httpMethod = "POST", value = "编辑合同,当id为空时新增合同,不为空时为更新合同信息")
    public Wrapper saveContract(@RequestBody PmcContractDto pmcContractDto) {
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        PmcContract pmcContract = new PmcContract();
        BeanUtils.copyProperties(pmcContractDto, pmcContract);
        pmcContractService.saveContract(pmcContract,loginAuthDto);
        return WrapMapper.ok();
    }

    @PostMapping("/getContractById/{id}")
    @ApiOperation(httpMethod = "POST", value = "根据合同id查询合同")
    public Wrapper<PmcContract> getContractById(@PathVariable Long id) {
        PmcContract pmcContract = pmcContractService.getContractById(id);
        return WrapMapper.ok(pmcContract);
    }

    @PostMapping("/getContactListByGroupId/{groupId}")
    @ApiOperation(httpMethod = "POST", value = "获取某个组织的合同列表")
    public Wrapper<List<PmcContract>> getContactListByGroupId(@ApiParam(value = "组织id") @PathVariable Long groupId) {
        List<PmcContract> pmcContractList = pmcContractService.getContactListByGroupId(groupId);
        return WrapMapper.ok(pmcContractList);
    }

    @PostMapping("/getContractListWithPage")
    @ApiOperation(httpMethod = "POST", value = "分页获取所有合同列表")
    public Wrapper<PageInfo> getContractListWithPage(@ApiParam(value = "分页排序参数") @RequestBody BaseQuery baseQuery) {
        PageInfo pageInfo = pmcContractService.getContractListWithPage(baseQuery);
        return WrapMapper.ok(pageInfo);
    }

    @PostMapping("/deleteContractById/{id}")
    @ApiOperation(httpMethod = "POST", value = "根据合同id删除合同")
    public Wrapper deleteContractById(@PathVariable Long id) {
        pmcContractService.deleteContractById(id);
        return WrapMapper.ok();
    }

    @PostMapping("getContactByAB/{partyAId}/{partyBId}")
    @ApiOperation(httpMethod = "POST", value = "获取甲乙双方签订的合同")
    public Wrapper<List<PmcContract>> getContactByAB(@ApiParam(value = "甲方id") @PathVariable Long partyAId,@ApiParam(value = "乙方id") @PathVariable Long partyBId) {
        List<PmcContract> pmcContractList = pmcContractService.getContactByAB(partyAId,partyBId);
        return WrapMapper.ok(pmcContractList);
    }










}
