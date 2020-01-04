package com.ananops.provider.service;

import com.ananops.provider.model.dto.PmcContractDto;
import com.ananops.provider.model.dto.PmcPayDto;
import com.ananops.provider.service.hystrix.PmcContractFeignHystrix;
import com.ananops.provider.service.hystrix.PmcProjectFeignHystrix;
import com.ananops.security.feign.OAuth2FeignAutoConfiguration;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Created By ChengHao On 2019/12/30
 */
@FeignClient(value = "ananops-provider-pmc",configuration = OAuth2FeignAutoConfiguration.class,fallback = PmcContractFeignHystrix.class)
public interface PmcContractFeignApi {

    /**
     * 获取甲乙双方合同的支付方式和支付金额
     * @param partyAId
     * @param partyBId
     * @return
     */
    @PostMapping("/api/contract/getContactByAB/{partyAId}/{partyBId}")
    Wrapper<List<PmcPayDto>> getContactByAB(@PathVariable(value = "partyAId") Long partyAId, @PathVariable(value = "partyBId") Long partyBId);

    /**
     * 根据合同id查询合同
     * @param contractId
     * @return
     */
    @PostMapping("/getContractById/{contractId}")
    Wrapper<PmcContractDto> getContractById(@PathVariable(value = "contractId") Long contractId);
}
