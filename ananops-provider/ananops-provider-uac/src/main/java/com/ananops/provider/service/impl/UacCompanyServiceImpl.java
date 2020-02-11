package com.ananops.provider.service.impl;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.provider.mapper.UacGroupMapper;
import com.ananops.provider.model.domain.UacGroup;
import com.ananops.provider.model.dto.CompanyDto;
import com.ananops.provider.model.dto.company.CompanyRegisterDto;
import com.ananops.provider.model.dto.group.GroupBindUserReqDto;
import com.ananops.provider.model.dto.user.UserRegisterDto;
import com.ananops.provider.service.SpcCompanyFeignApi;
import com.ananops.provider.service.UacCompanyService;
import com.ananops.provider.service.UacGroupService;
import com.ananops.provider.service.UacUserService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 操作加盟服务商Company的Service接口实现类
 *
 * @author Bingyue Duan
 * @version 1.0
 * @date 2020-01-29 11:20
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UacCompanyServiceImpl implements UacCompanyService {

    @Resource
    private UacUserService uacUserService;

    @Resource
    private UacGroupService uacGroupService;

    @Resource
    private SpcCompanyFeignApi spcCompanyFeignApi;

    @Override
    public void register(CompanyRegisterDto company) {
        // 校验注册信息
        validateRegisterInfo(company);
        // 构建UAC User注册Dto
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        UacGroup uacGroup = new UacGroup();
        CompanyDto companyDto = new CompanyDto();
        try {
            // 绑定用户注册信息
            BeanUtils.copyProperties(userRegisterDto, company);
            userRegisterDto.setLoginName(company.getGroupName());
            userRegisterDto.setMobileNo(company.getContactPhone());

            // 绑定组织注册信息
            BeanUtils.copyProperties(uacGroup, company);
            // 服务商新注册默认Pid为平台超级用户管理员
            uacGroup.setPid(1L);
            // 新注册服务商默认地址为中国/北京
            uacGroup.setAddressList(Arrays.asList(368100109646176256L, 368100109679730688L));

            // 拷贝服务商注册信息到SPC注册对象
            BeanUtils.copyProperties(companyDto, company);
        } catch (Exception e) {
            log.error("服务商Dto与用户Dto属性拷贝异常");
            e.printStackTrace();
        }
        log.info("注册用户. userRegisterDto={}", userRegisterDto);
        Long uacUserId = uacUserService.register(userRegisterDto);
        log.info("注册用户.【ok】uacUserId={}", uacUserId);

        // 构造创建对象信息
        LoginAuthDto loginAuthDto = new LoginAuthDto();
        loginAuthDto.setUserId(uacUserId);
        loginAuthDto.setUserName(company.getGroupName());
        loginAuthDto.setLoginName(company.getGroupName());

        log.info("注册组织. uacGroup={}", uacGroup);
        // 这里默认新注册服务商会返回新组成生成的GroupId
        Long uacGroupId = uacGroupService.saveUacGroup(uacGroup, loginAuthDto);
        log.info("注册组织.【ok】uacGroupId={}", uacGroupId);

        if (uacUserId != null && uacGroupId != null) {
            GroupBindUserReqDto groupBindUserReqDto = new GroupBindUserReqDto();
            groupBindUserReqDto.setGroupId(uacGroupId);
            groupBindUserReqDto.setUserIdList(Arrays.asList(uacUserId));
            log.info("绑定用户到组织. groupBindUserReqDto={}", groupBindUserReqDto);
            // 加一操作为了规避"组织和当前登录用户之间的越权检查"
            loginAuthDto.setUserId(uacUserId + 1);
            uacGroupService.bindUacUser4Group(groupBindUserReqDto, loginAuthDto);
            log.info("绑定用户到组织.【ok】");

            companyDto.setGroupId(uacGroupId);
            companyDto.setUserId(uacUserId);
            log.info("在SPC中注册服务商对象。companyDto={}", companyDto);
            spcCompanyFeignApi.registerNewCompany(companyDto);
        }
    }

    /**
     * 校验注册信息
     *
     * @param companyRegisterDto 注册的对象
     */
    private void validateRegisterInfo(CompanyRegisterDto companyRegisterDto) {
        String mobileNo = companyRegisterDto.getContactPhone();

        Preconditions.checkArgument(!StringUtils.isEmpty(companyRegisterDto.getGroupName()), ErrorCodeEnum.UAC10011007.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(companyRegisterDto.getEmail()), ErrorCodeEnum.UAC10011018.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(companyRegisterDto.getGroupCode()), ErrorCodeEnum.SPC100850010.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(mobileNo), "手机号不能为空");
        Preconditions.checkArgument(!StringUtils.isEmpty(companyRegisterDto.getLoginPwd()), ErrorCodeEnum.UAC10011014.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(companyRegisterDto.getConfirmPwd()), ErrorCodeEnum.UAC10011009.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(companyRegisterDto.getPhoneSmsCode()), "短信验证码不能为空");
        Preconditions.checkArgument(companyRegisterDto.getLoginPwd().equals(companyRegisterDto.getConfirmPwd()), "两次密码不一致");
    }
}
