package com.ananops.provider.web.frontend;

import com.ananops.core.support.BaseController;
import com.ananops.provider.model.dto.CompanyRegisterDto;
import com.ananops.provider.service.SpcCompanyService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 服务商不认证的URL请求
 *
 * Created by bingyueduan on 2019/12/29.
 */
@RestController
@RequestMapping(value = "/auth")
@Api(value = "Web - AuthSpcRestController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AuthSpcRestController extends BaseController {

    @Resource
    private SpcCompanyService spcCompanyService;

    /**
     * 注册一个服务商
     *
     * @param company 初始注册信息
     *
     * @return the Wrapper
     */
    @PostMapping(value = "/company/registCompany")
    @ApiOperation(httpMethod = "POST", value = "注册服务商")
    public Wrapper registerSpcCompany(CompanyRegisterDto company) {
        logger.info("注册服务商");
        spcCompanyService.register(company);
        return WrapMapper.ok();
    }
}
