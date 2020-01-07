package com.ananops.provider.web.frontend;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.annotation.LogAnnotation;
import com.ananops.core.support.BaseController;
import com.ananops.provider.model.domain.SpcCompany;
import com.ananops.provider.model.dto.CompanyStatusDto;
import com.ananops.provider.model.dto.ModifyCompanyStatusDto;
import com.ananops.provider.model.vo.CompanyVo;
import com.ananops.provider.service.SpcCompanyService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 服务商模块对外提供操作Company(公司)的Restful接口
 *
 * Created by bingyueduan on 2019/12/28.
 */
@RestController
@RequestMapping(value = "/company", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - SpcCompanyController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SpcCompanyController extends BaseController {

    @Resource
    private SpcCompanyService spcCompanyService;

    @PostMapping(value = "/queryAllCompanys")
    @LogAnnotation
    @ApiOperation(httpMethod = "POST", value = "获取加盟服务商列表")
    public Wrapper<PageInfo<SpcCompany>> queryAllCompanys(@ApiParam(name = "spcCompany", value = "公司信息") @RequestBody SpcCompany spcCompany) {
        logger.info("分页查询服务商列表, spcCompany={}", spcCompany);
        PageHelper.startPage(spcCompany.getPageNum(), spcCompany.getPageSize());
        spcCompany.setOrderBy("update_time desc");
        List<SpcCompany> companyVoList = spcCompanyService.queryAllCompanys(spcCompany);
        return WrapMapper.ok(new PageInfo<>(companyVoList));
    }

    /**
     * 根据Id修改公司状态.
     *
     * @param modifyCompanyStatusDto the modify Company status dto
     *
     * @return the wrapper
     */
    @PostMapping(value = "/modifyCompanyStatusById")
    @LogAnnotation
    @ApiOperation(httpMethod = "POST", value = "根据Id修改公司状态")
    public Wrapper<Integer> modifyCompanyStatusById(@ApiParam(name = "modifyCompanyStatusDto", value = "公司禁用/激活Dto") @RequestBody ModifyCompanyStatusDto modifyCompanyStatusDto) {
        logger.info(" 根据Id修改公司状态 modifyCompanyStatusDto={}", modifyCompanyStatusDto);
        int result = spcCompanyService.modifyCompanyStatusById(modifyCompanyStatusDto);
        return handleResult(result);
    }

    /**
     * 根据公司状态分页查询公司列表.
     *
     * @param CompanyStatusDto
     *
     * @return the wrapper
     */
    @PostMapping(value = "/queryListWithStatus")
    @LogAnnotation
    @ApiOperation(httpMethod = "POST", value = "根据公司状态分页查询公司列表")
    public Wrapper<PageInfo<CompanyVo>> queryListWithStatus(@ApiParam(name = "companyStatusDto", value = "公司状态查询Dto") @RequestBody CompanyStatusDto companyStatusDto) {
        logger.info("根据公司状态分页查询公司列表 companyStatusDto={}", companyStatusDto);
        PageHelper.startPage(companyStatusDto.getPageNum(), companyStatusDto.getPageSize());
        companyStatusDto.setOrderBy("update_time desc");
        List<CompanyVo> companyVoList = spcCompanyService.queryListByStatus(companyStatusDto);
        return WrapMapper.ok(new PageInfo<>(companyVoList));
    }

    /**
     * 根据公司Id查询公司信息.
     *
     * @param companyId the company id
     *
     * @return the spc company by id
     */
    @PostMapping(value = "/getSpcCompanyById/{companyId}")
    @LogAnnotation
    @ApiOperation(httpMethod = "POST", value = "根据用户Id查询用户信息")
    public Wrapper<CompanyVo> getSpcCompanyById(@ApiParam(name = "companyId", value = "公司ID") @PathVariable Long companyId) {
        logger.info("getSpcCompanyById - 根据公司Id查询公司信息. companyId={}", companyId);
        CompanyVo uacCompany = spcCompanyService.queryByCompanyId(companyId);
        logger.info("getUacUserById - 根据公司Id查询公司信息. [OK] uacCompany={}", uacCompany);
        return WrapMapper.ok(uacCompany);
    }

    /**
     * 根据公司Id保存公司信息.
     *
     * @param companyVo 编辑之后的对象
     *
     * @return the spc company by id
     */
    @PostMapping(value = "/save")
    @LogAnnotation
    @ApiOperation(httpMethod = "POST", value = "根据公司Id保存公司信息")
    public Wrapper<Integer> addUacCompany(@ApiParam(name = "companyVo", value = "公司ID及详细信息") @RequestBody CompanyVo companyVo) {
        logger.info(" 新增公司 companyVo={}", companyVo);
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        spcCompanyService.saveUacCompany(companyVo, loginAuthDto);
        return WrapMapper.ok();
    }
}
