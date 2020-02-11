package com.ananops.provider.web.rpc;

import com.ananops.PublicUtil;
import com.ananops.core.support.BaseController;
import com.ananops.provider.model.domain.PmcContract;
import com.ananops.provider.model.dto.PmcContractDto;
import com.ananops.provider.model.dto.PmcPayDto;
import com.ananops.provider.service.PmcContractFeignApi;
import com.ananops.provider.service.PmcContractService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By ChengHao On 2019/12/30
 */
@RefreshScope
@RestController
@Api(value = "API - PmcContractFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PmcContractFeignClient extends BaseController implements PmcContractFeignApi {
    @Autowired
    PmcContractService pmcContractService;

    @Override
    @ApiOperation(httpMethod = "POST", value = "获取甲乙双方合同的支付方式和支付金额")
    public Wrapper<List<PmcPayDto>> getContactByAB(@PathVariable(value = "partyAId") Long partyAId, @PathVariable(value = "partyBId") Long partyBId) {
        List<PmcContract> pmcContractList = pmcContractService.getContactByAB(partyAId,partyBId);
        List<PmcPayDto> pmcPayDtoList = new ArrayList<>();
        if (PublicUtil.isNotEmpty(pmcContractList)) {
            for (PmcContract pmcContract : pmcContractList) {
                PmcPayDto pmcPayDto = new PmcPayDto();
                pmcPayDto.setPaymentType(pmcContract.getPaymentType());
                pmcPayDto.setPaymentMoney(pmcContract.getPaymentMoney());
                pmcPayDtoList.add(pmcPayDto);
            }
            return WrapMapper.ok(pmcPayDtoList);
        }
        return WrapMapper.ok();
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = " 根据合同id查询合同")
    public Wrapper<PmcContractDto> getContractById(@PathVariable(value = "contractId") Long contractId) {
        PmcContract pmcContract = pmcContractService.getContractById(contractId);
        PmcContractDto pmcContractDto = new PmcContractDto();
        BeanUtils.copyProperties(pmcContract, pmcContractDto);
        return WrapMapper.ok(pmcContractDto);
    }
}
