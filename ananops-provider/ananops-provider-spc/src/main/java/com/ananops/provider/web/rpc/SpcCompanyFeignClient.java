package com.ananops.provider.web.rpc;

import com.ananops.PubUtils;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.core.support.BaseController;
import com.ananops.provider.model.dto.CompanyDto;
import com.ananops.provider.model.vo.CompanyVo;
import com.ananops.provider.service.SpcCompanyFeignApi;
import com.ananops.provider.service.SpcCompanyService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import com.google.common.base.Preconditions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 加盟服务商提供的对Company操作的Feign客户端
 *
 * Created by bingyueduan on 2019/12/28.
 */
@RefreshScope
@RestController
@Api(value = "API - SpcCompanyFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SpcCompanyFeignClient extends BaseController implements SpcCompanyFeignApi {

    @Resource
    private SpcCompanyService spcCompanyService;

    @Override
    @ApiOperation(httpMethod = "POST", value = "根据服务商Id获取服务商")
    public Wrapper<Integer> getCompanyById(@RequestBody CompanyDto companyDto) {
        logger.info("查询服务商. companyDto={}", companyDto);
        Preconditions.checkArgument(!PubUtils.isNull(companyDto, companyDto.getId()), ErrorCodeEnum.SPC100850020.msg());
        int result = spcCompanyService.getCompanyById(companyDto);
        return WrapMapper.ok(result);
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "根据服务商Id获取服务商的详情")
    public Wrapper<CompanyVo> getCompanyDetailsById(@PathVariable(value = "companyId") Long companyId){
        logger.info("查询服务商详情. companyId={}", companyId);
        CompanyVo companyVo = spcCompanyService.queryByCompanyId(companyId);
        return WrapMapper.ok(companyVo);
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "注册一个新的服务商")
    public Wrapper<Integer> registerNewCompany(@RequestBody CompanyDto companyDto) {
        logger.info("服务商初始注册. companyDto={}", companyDto);
        Preconditions.checkArgument(!PubUtils.isNull(companyDto, companyDto.getUserId()), ErrorCodeEnum.UAC10011001.msg());
        Preconditions.checkArgument(!PubUtils.isNull(companyDto, companyDto.getGroupId()), ErrorCodeEnum.UAC10015010.msg());
        int result = spcCompanyService.registerNew(companyDto);
        return WrapMapper.ok(result);
    }
}
