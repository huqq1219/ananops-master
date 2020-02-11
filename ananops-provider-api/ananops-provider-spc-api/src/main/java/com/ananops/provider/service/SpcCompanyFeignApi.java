package com.ananops.provider.service;

import com.ananops.provider.model.dto.CompanyDto;
import com.ananops.provider.model.vo.CompanyVo;
import com.ananops.provider.service.hystrix.SpcCompanyFeignHystrix;
import com.ananops.security.feign.OAuth2FeignAutoConfiguration;
import com.ananops.wrapper.Wrapper;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * SPC(加盟服务商)模块Feign API
 *
 * Created by bingyueduan on 2019/12/28.
 */
@FeignClient(value = "ananops-provider-spc", configuration = OAuth2FeignAutoConfiguration.class, fallback = SpcCompanyFeignHystrix.class)
public interface SpcCompanyFeignApi {

    @PostMapping(value = "/api/company/getCompanyById")
    Wrapper<Integer> getCompanyById(@RequestBody CompanyDto companyDto);

    @PostMapping(value = "/api/company/newRegister")
    Wrapper<Integer> registerNewCompany(@RequestBody CompanyDto companyDto);

    @PostMapping(value = "/api/company/getCompanyDetailsById/{companyId}")
    Wrapper<CompanyVo> getCompanyDetailsById(@PathVariable(value = "companyId") Long companyId);
}
