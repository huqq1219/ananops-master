package com.ananops.provider.service.hystrix;

import com.ananops.provider.model.dto.CompanyDto;
import com.ananops.provider.service.SpcCompanyFeignApi;
import com.ananops.wrapper.Wrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * SPC(加盟服务商)模块Feign Hystrix
 *
 * Created by bingyueduan on 2019/12/28.
 */
@Component
public class SpcCompanyFeignHystrix implements SpcCompanyFeignApi {

    @Override
    public Wrapper<Integer> getCompanyById(@RequestBody CompanyDto companyDto) {
        return null;
    }
}
