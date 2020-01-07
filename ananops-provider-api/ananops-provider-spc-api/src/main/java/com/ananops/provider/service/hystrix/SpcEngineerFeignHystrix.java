package com.ananops.provider.service.hystrix;

import com.ananops.provider.model.dto.EngineerDto;
import com.ananops.provider.service.SpcEngineerFeignApi;
import com.ananops.wrapper.Wrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 工程师操作Feign Hystrix
 *
 * Created by bingyueduan on 2019/12/30.
 */
@Component
public class SpcEngineerFeignHystrix implements SpcEngineerFeignApi {

    @Override
    public Wrapper<List<EngineerDto>> getEngineersByProjectId(@PathVariable(value = "projectId") Long projectId) {
        return null;
    }

    @Override
    public Wrapper<EngineerDto> getEngineerById(@PathVariable(value = "engineerId") Long engineerId) {
        return null;
    }

    @Override
    public Wrapper<List<EngineerDto>> getEngineersByBatchId(@RequestParam("engineerIdList") List<Long> engineerIdList) {
        return null;
    }
}
