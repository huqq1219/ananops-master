package com.ananops.provider.service;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.provider.model.dto.company.CompanyRegisterDto;

/**
 * Uac内操作Company的Service接口
 *
 * Created by bingyueduan on 2020/1/29.
 */
public interface UacCompanyService {

    /**
     * 注册服务商
     *
     * @param company
     *
     */
    void register(CompanyRegisterDto company);
}
