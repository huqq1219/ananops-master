package com.ananops.provider.service;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.IService;
import com.ananops.provider.model.domain.SpcCompany;
import com.ananops.provider.model.dto.CompanyDto;
import com.ananops.provider.model.dto.CompanyRegisterDto;
import com.ananops.provider.model.dto.CompanyStatusDto;
import com.ananops.provider.model.dto.ModifyCompanyStatusDto;
import com.ananops.provider.model.vo.CompanyVo;

import java.util.List;

/**
 * 操作加盟服务商Company的Service接口
 *
 * Created by bingyueduan on 2019/12/28.
 */
public interface SpcCompanyService extends IService<SpcCompany> {

    /**
     * 根据服务商Id查询服务商
     *
     * @param companyDto
     *
     * @return
     */
    int getCompanyById(CompanyDto companyDto);

    /**
     * 查询所有服务商列表
     *
     * @param spcCompany
     *
     * @return
     */
    List<SpcCompany> queryAllCompanys(SpcCompany spcCompany);

    /**
     * 注册服务商
     *
     * @param company
     *
     */
    void register(CompanyRegisterDto company);

    /**
     * 根据公司Id修改公司状态
     *
     * @param modifyCompanyStatusDto 绑定状态
     *
     * @return 成功:1 失败:0
     */
    int modifyCompanyStatusById(ModifyCompanyStatusDto modifyCompanyStatusDto);

    /**
     * 根据公司状态分页查询公司信息
     *
     * @param spcCompany 分页查询数据
     *
     * @return 返回公司列表
     */
    List<CompanyVo> queryListByStatus(CompanyStatusDto companyStatusDto);

    /**
     * 根据公司Id查询公司详细信息
     *
     * @param companyId 公司Id
     *
     * @return 返回公司详情
     */
    CompanyVo queryByCompanyId(Long companyId);

    /**
     * 根据公司Id编辑并保存公司详细信息
     *
     * @param companyVo 公司信息
     *
     * @param loginAuthDto 登录信息
     */
    void saveUacCompany(CompanyVo companyVo, LoginAuthDto loginAuthDto);
}
