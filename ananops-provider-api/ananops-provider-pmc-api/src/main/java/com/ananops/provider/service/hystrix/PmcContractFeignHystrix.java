package com.ananops.provider.service.hystrix;

import com.ananops.provider.model.dto.PmcContractDto;
import com.ananops.provider.model.dto.PmcPayDto;
import com.ananops.provider.service.PmcContractFeignApi;
import com.ananops.wrapper.Wrapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created By ChengHao On 2019/12/30
 */
@Component
public class PmcContractFeignHystrix implements PmcContractFeignApi {
    @Override
    public Wrapper<List<PmcPayDto>> getContactByAB(Long partyAId, Long partyBId) {
        return null;
    }

    @Override
    public Wrapper<PmcContractDto> getContractById(Long contractId) {
        return null;
    }
}
