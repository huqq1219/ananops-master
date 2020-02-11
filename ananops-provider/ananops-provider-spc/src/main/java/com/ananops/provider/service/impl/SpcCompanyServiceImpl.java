package com.ananops.provider.service.impl;

import com.ananops.PublicUtil;
import com.ananops.base.constant.GlobalConstant;
import com.ananops.base.dto.LoginAuthDto;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.core.support.BaseService;
import com.ananops.provider.mapper.SpcCompanyMapper;
import com.ananops.provider.model.domain.SpcCompany;
import com.ananops.provider.model.dto.CompanyDto;
import com.ananops.provider.model.dto.CompanyStatusDto;
import com.ananops.provider.model.dto.ModifyCompanyStatusDto;
import com.ananops.provider.model.dto.attachment.OptUploadFileByteInfoReqDto;
import com.ananops.provider.model.dto.group.GroupSaveDto;
import com.ananops.provider.model.dto.group.GroupStatusDto;
import com.ananops.provider.model.dto.oss.OptUploadFileReqDto;
import com.ananops.provider.model.dto.oss.OptUploadFileRespDto;
import com.ananops.provider.model.dto.user.IdStatusDto;
import com.ananops.provider.model.service.UacGroupFeignApi;
import com.ananops.provider.model.vo.CompanyVo;
import com.ananops.provider.service.OpcOssFeignApi;
import com.ananops.provider.service.SpcCompanyService;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.xiaoleilu.hutool.io.FileTypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
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
    private UacGroupFeignApi uacGroupFeignApi;

    @Resource
    private OpcOssFeignApi opcOssFeignApi;

    @Override
    public int getCompanyById(CompanyDto companyDto) {
        return spcCompanyMapper.selectCount(new SpcCompany());
    }

    @Override
    public List<SpcCompany> queryAllCompanys(SpcCompany spcCompany) {
        return spcCompanyMapper.select(spcCompany);
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
        logger.info("queryByCompanyId - 根据公司Id(groupId)查询公司信息接口. companyId={}", companyId);
        CompanyVo companyVo = new CompanyVo();
        SpcCompany queryC = new SpcCompany();
        queryC.setGroupId(companyId);
        SpcCompany spcCompany = spcCompanyMapper.selectOne(queryC);
        if (companyId != null) {
            GroupSaveDto groupSaveDto = uacGroupFeignApi.getUacGroupById(companyId).getResult();
            try {
                if (spcCompany != null)
                    BeanUtils.copyProperties(companyVo, spcCompany);
                if (groupSaveDto != null)
                    BeanUtils.copyProperties(companyVo, groupSaveDto);
            } catch (Exception e) {
                logger.error("queryByCompanyId 服务商Dto与用户组Dto属性拷贝异常");
                e.printStackTrace();
            }
        }
        return companyVo;
    }

    @Override
    public List<CompanyVo> queryByLikeCompanyName(String companyName) {
        logger.info("queryByLikeCompanyName - 根据公司名称模糊查询公司信息. companyName={}", companyName);
        List<CompanyVo> companyVos = new ArrayList<>();
        List<GroupSaveDto> groupSaveDtos = uacGroupFeignApi.getUacGroupByLikeName(companyName).getResult();
        if (groupSaveDtos != null) {
            for (GroupSaveDto groupSaveDto : groupSaveDtos) {
                CompanyVo companyVo = new CompanyVo();
                Long groupId = groupSaveDto.getId();
                SpcCompany queryC = new SpcCompany();
                queryC.setGroupId(groupId);
                SpcCompany spcCompany = spcCompanyMapper.selectOne(queryC);
                try {
                    if (spcCompany != null)
                        BeanUtils.copyProperties(companyVo, spcCompany);
                    BeanUtils.copyProperties(companyVo, groupSaveDto);
                } catch (Exception e) {
                    logger.error("queryByCompanyId 服务商Dto与用户组Dto属性拷贝异常");
                    e.printStackTrace();
                }
                companyVos.add(companyVo);
            }

        }
        return companyVos;
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

    @Override
    public int registerNew(CompanyDto companyDto) {
        Date row = new Date();
        // 封装注册信息
        long id = generateId();
        SpcCompany spcCompany = new SpcCompany();
        spcCompany.setId(id);
        if (companyDto.getGroupId() != null) {
            spcCompany.setGroupId(companyDto.getGroupId());
        } else {
            return 1;
        }
        if (companyDto.getUserId() != null)
            spcCompany.setUserId(companyDto.getUserId());
        spcCompany.setCreatorId(id);
        spcCompany.setCreator(companyDto.getGroupName());
        spcCompany.setLastOperatorId(id);
        spcCompany.setLastOperator(companyDto.getGroupName());
        spcCompany.setCreatedTime(row);
        logger.info("注册服务商. SpcCompany={}", spcCompany);
        spcCompanyMapper.insertSelective(spcCompany);
        return 0;
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

    @Override
    public List<OptUploadFileRespDto> uploadCompanyFile(MultipartHttpServletRequest multipartRequest, OptUploadFileReqDto optUploadFileReqDto, LoginAuthDto loginAuthDto) {
        // 这里的filePath来区分照片类型，有以下三种:
        // 法人身份证照片：legalPersonIdPhoto
        // 营业执照照片：businessLicensePhoto
        // 账户开户许可证照片：accountOpeningLicense
        String filePath = optUploadFileReqDto.getFilePath();
        Long userId = loginAuthDto.getUserId();
        String userName = loginAuthDto.getUserName();
        List<OptUploadFileRespDto> result = Lists.newArrayList();
        try {
            List<MultipartFile> fileList = multipartRequest.getFiles("file");
            if (fileList.isEmpty()) {
                return result;
            }

            for (MultipartFile multipartFile : fileList) {
                String fileName = multipartFile.getOriginalFilename();
                if (PublicUtil.isEmpty(fileName)) {
                    continue;
                }
                Preconditions.checkArgument(multipartFile.getSize() <= GlobalConstant.FILE_MAX_SIZE, "上传文件不能大于5M");
                InputStream inputStream = multipartFile.getInputStream();

                String inputStreamFileType = FileTypeUtil.getType(inputStream);
                // 将上传文件的字节流封装到到Dto对象中
                OptUploadFileByteInfoReqDto optUploadFileByteInfoReqDto = new OptUploadFileByteInfoReqDto();
                optUploadFileByteInfoReqDto.setFileByteArray(multipartFile.getBytes());
                optUploadFileByteInfoReqDto.setFileName(fileName);
                optUploadFileByteInfoReqDto.setFileType(inputStreamFileType);
                optUploadFileReqDto.setUploadFileByteInfoReqDto(optUploadFileByteInfoReqDto);
                // 设置不同文件路径来区分图片
                optUploadFileReqDto.setFilePath("ananops/spc/company/" + userId + "/" + filePath + "/");
                optUploadFileReqDto.setUserId(userId);
                optUploadFileReqDto.setUserName(userName);
                OptUploadFileRespDto optUploadFileRespDto = opcOssFeignApi.uploadFile(optUploadFileReqDto).getResult();
                result.add(optUploadFileRespDto);
            }
        } catch (IOException e) {
            logger.error("上传文件失败={}", e.getMessage(), e);
        }
        return result;
    }
}
