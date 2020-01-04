package com.ananops.provider.service.impl;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.core.support.BaseService;
import com.ananops.provider.mapper.SpcCompanyMapper;
import com.ananops.provider.model.domain.SpcCompany;
import com.ananops.provider.model.dto.CompanyDto;
import com.ananops.provider.model.dto.CompanyRegisterDto;
import com.ananops.provider.model.dto.CompanyStatusDto;
import com.ananops.provider.model.dto.ModifyCompanyStatusDto;
import com.ananops.provider.model.dto.group.GroupSaveDto;
import com.ananops.provider.model.dto.group.GroupStatusDto;
import com.ananops.provider.model.dto.user.IdStatusDto;
import com.ananops.provider.model.dto.user.UserRegisterDto;
import com.ananops.provider.model.service.UacGroupFeignApi;
import com.ananops.provider.model.service.UacUserFeignApi;
import com.ananops.provider.model.vo.CompanyVo;
import com.ananops.provider.service.SpcCompanyService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 操作加盟服务商Company的Service接口实现类
 *
 * Created by bingyueduan on 2019/12/28.
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SpcCompanyServiceImpl extends BaseService<SpcCompany> implements SpcCompanyService {

    @Resource
    private SpcCompanyMapper spcCompanyMapper;

    @Resource
    private UacUserFeignApi uacUserFeignApi;

    @Resource
    private UacGroupFeignApi uacGroupFeignApi;

    @Override
    public int getCompanyById(CompanyDto companyDto) {
        return spcCompanyMapper.selectCount(new SpcCompany());
    }

    @Override
    public List<SpcCompany> queryAllCompanys(SpcCompany spcCompany) {
        return spcCompanyMapper.select(spcCompany);
    }

    @Override
    public void register(CompanyRegisterDto company) {
        // 校验注册信息
        validateRegisterInfo(company);
        // 构建UAC User注册Dto
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        GroupSaveDto groupSaveDto = new GroupSaveDto();
        try {
            BeanUtils.copyProperties(userRegisterDto, company);
            BeanUtils.copyProperties(groupSaveDto, company);
        } catch (Exception e) {
            logger.error("服务商Dto与用户Dto属性拷贝异常");
            e.printStackTrace();
        }
        Long uacUserId = uacUserFeignApi.userRegister(userRegisterDto).getResult();
        Long uacGroupId = uacGroupFeignApi.groupSave(groupSaveDto).getResult();

        if (!StringUtils.isEmpty(uacUserId) && !StringUtils.isEmpty(uacGroupId)) {
            Date row = new Date();
            // 封装注册信息
            long id = generateId();
            SpcCompany spcCompany = new SpcCompany();
            spcCompany.setId(id);
            spcCompany.setGroupId(uacGroupId);
            spcCompany.setUserId(uacUserId);
            spcCompany.setCreatorId(id);
            spcCompany.setCreator(company.getGroupName());
            spcCompany.setLastOperatorId(id);
            spcCompany.setLastOperator(company.getGroupName());
            logger.info("注册服务商. SpcCompany={}", spcCompany);
            spcCompanyMapper.insertSelective(spcCompany);
        }
    }



    @Override
    public int modifyCompanyStatusById(ModifyCompanyStatusDto modifyCompanyStatusDto) {
        Long companyId = modifyCompanyStatusDto.getCompanyId();
        String status = modifyCompanyStatusDto.getStatus();
        if (!StringUtils.isEmpty(companyId) && !StringUtils.isEmpty(status)) {
            Long uacGroupId = spcCompanyMapper.selectByPrimaryKey(companyId).getGroupId();
            IdStatusDto modifyGroupStatusDto = new IdStatusDto();
            modifyGroupStatusDto.setId(uacGroupId);
            modifyGroupStatusDto.setStatus(Integer.valueOf(status));
            return (int)uacGroupFeignApi.modifyGroupStatus(modifyGroupStatusDto).getResult();
        }
        return 0;
    }

    @Override
    public List<CompanyVo> queryListByStatus(CompanyStatusDto companyStatusDto) {
        List<CompanyVo> results = new ArrayList<>();
        GroupStatusDto groupStatusDto = new GroupStatusDto();
        try {
            BeanUtils.copyProperties(groupStatusDto, companyStatusDto);
        } catch (Exception e) {
            logger.error("服务商Dto与用户组Dto属性拷贝异常");
            e.printStackTrace();
        }
        List<GroupSaveDto> groupSaveDtos = uacGroupFeignApi.queryListByStatus(groupStatusDto).getResult();
        for (GroupSaveDto groupSaveDto : groupSaveDtos) {
            Long groupId = groupSaveDto.getId();
            CompanyVo companyVo = new CompanyVo();
            SpcCompany getSpcCompany = new SpcCompany();
            getSpcCompany.setGroupId(groupId);
            SpcCompany spcCompany = spcCompanyMapper.selectOne(getSpcCompany);
            try {
                BeanUtils.copyProperties(companyVo, groupSaveDto);
                BeanUtils.copyProperties(companyVo, spcCompany);
            } catch (Exception e) {
                logger.error("queryListByStatus 服务商Dto与用户组Dto属性拷贝异常");
                e.printStackTrace();
            }
            results.add(companyVo);
        }
        return results;
    }

    @Override
    public CompanyVo queryByCompanyId(Long companyId) {
        logger.info("queryByCompanyId - 根据公司Id查询公司信息接口. companyId={}", companyId);
        CompanyVo companyVo = new CompanyVo();
        SpcCompany spcCompany = spcCompanyMapper.selectByPrimaryKey(companyId);
        Long groupId = spcCompany.getGroupId();
        if (!StringUtils.isEmpty(groupId)) {
            GroupSaveDto groupSaveDto = uacGroupFeignApi.getUacGroupById(groupId).getResult();
            try {
                BeanUtils.copyProperties(companyVo, spcCompany);
                BeanUtils.copyProperties(companyVo, groupSaveDto);
            } catch (Exception e) {
                logger.error("queryByCompanyId 服务商Dto与用户组Dto属性拷贝异常");
                e.printStackTrace();
            }
        }
        return companyVo;
    }

    @Override
    public void saveUacCompany(CompanyVo companyVo, LoginAuthDto loginAuthDto) {
        Long companyId = companyVo.getId();
        SpcCompany queryResult = spcCompanyMapper.selectByPrimaryKey(companyId);
        Long groupId = queryResult.getGroupId();
        // 校验保存信息
        validateCompanyVo(companyVo);

        GroupSaveDto groupSaveDto = new GroupSaveDto();
        groupSaveDto.setId(groupId);
        try {
            BeanUtils.copyProperties(groupSaveDto, companyVo);
        } catch (Exception e) {
            logger.error("服务商Dto与用户组Dto属性拷贝异常");
            e.printStackTrace();
        }
        Long uacGroupId = uacGroupFeignApi.groupSave(groupSaveDto).getResult();

        if (!StringUtils.isEmpty(companyId) && !StringUtils.isEmpty(uacGroupId)) {
            Date row = new Date();
            // 封装更新公司信息
            SpcCompany spcCompany = new SpcCompany();
            spcCompany.setId(companyId);
            spcCompany.setGroupId(uacGroupId);
            spcCompany.setLastOperatorId(loginAuthDto.getUserId());
            spcCompany.setLastOperator(loginAuthDto.getLoginName());
            try {
                BeanUtils.copyProperties(spcCompany, companyVo);
            } catch (Exception e) {
                logger.error("服务商Dto与用户组Dto属性拷贝异常");
                e.printStackTrace();
            }
            logger.info("注册服务商. SpcCompany={}", spcCompany);
            spcCompanyMapper.insertSelective(spcCompany);
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


    /**
     * 校验保存信息
     *
     * @param companyVo 保存更新的对象
     */
    private void validateCompanyVo(CompanyVo companyVo) {
        String mobileNo = companyVo.getContactPhone();

        Preconditions.checkArgument(!StringUtils.isEmpty(companyVo.getGroupName()), ErrorCodeEnum.UAC10011007.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(companyVo.getGroupCode()), ErrorCodeEnum.SPC100850010.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(mobileNo), "手机号不能为空");
        Preconditions.checkArgument(!StringUtils.isEmpty(companyVo.getLegalPersonName()), ErrorCodeEnum.SPC100850011.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(companyVo.getAccountNumber()), ErrorCodeEnum.SPC100850012.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(companyVo.getAccountOpeningLicense()), ErrorCodeEnum.SPC100850013.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(companyVo.getLicenseType()), ErrorCodeEnum.SPC100850014.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(companyVo.getExpirationDate()), ErrorCodeEnum.SPC100850015.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(companyVo.getBusinessLicensePhoto()), ErrorCodeEnum.SPC100850016.msg());
    }

}
