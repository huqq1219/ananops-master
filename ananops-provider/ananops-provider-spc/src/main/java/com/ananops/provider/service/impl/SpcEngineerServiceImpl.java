package com.ananops.provider.service.impl;

import com.ananops.PublicUtil;
import com.ananops.base.constant.GlobalConstant;
import com.ananops.base.dto.LoginAuthDto;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.core.support.BaseService;
import com.ananops.provider.mapper.SpcCompanyEngineerMapper;
import com.ananops.provider.mapper.SpcCompanyMapper;
import com.ananops.provider.mapper.SpcEngineerMapper;
import com.ananops.provider.model.domain.SpcCompany;
import com.ananops.provider.model.domain.SpcEngineer;
import com.ananops.provider.model.dto.EngineerDto;
import com.ananops.provider.model.dto.EngineerRegisterDto;
import com.ananops.provider.model.dto.EngineerStatusDto;
import com.ananops.provider.model.dto.ModifyEngineerStatusDto;
import com.ananops.provider.model.dto.user.IdStatusDto;
import com.ananops.provider.model.dto.user.UserInfoDto;
import com.ananops.provider.model.service.UacGroupFeignApi;
import com.ananops.provider.model.service.UacUserFeignApi;
import com.ananops.provider.model.vo.EngineerSimpleVo;
import com.ananops.provider.model.vo.EngineerVo;
import com.ananops.provider.service.PmcProjectEngineerFeignApi;
import com.ananops.provider.service.SpcEngineerService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * 操作加盟服务商Engineer的Service接口实现类
 *
 * Created by bingyueduan on 2019/12/30.
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SpcEngineerServiceImpl extends BaseService<SpcEngineer> implements SpcEngineerService {

    @Resource
    private SpcEngineerMapper spcEngineerMapper;

    @Resource
    private SpcEngineerService spcEngineerService;

    @Resource
    private SpcCompanyMapper spcCompanyMapper;

    @Resource
    private SpcCompanyEngineerMapper spcCompanyEngineerMapper;

    @Resource
    private PmcProjectEngineerFeignApi pmcProjectEngineerFeignApi;

    @Resource
    private UacUserFeignApi uacUserFeignApi;

    @Resource
    private UacGroupFeignApi uacGroupFeignApi;


    @Override
    public List<EngineerDto> getEngineersByProjectId(Long projectId) {
        List<EngineerDto> result = new ArrayList<>();
        List<Long> engineersId = pmcProjectEngineerFeignApi.getEngineersIdByProjectId(projectId).getResult();
        for (Long engineerId : engineersId) {
            result.add(getEngineerDto(engineerId));
        }
        return result;
    }

    @Override
    public EngineerDto getEngineerById(Long engineerId) {
        return getEngineerDto(engineerId);
    }

    @Override
    public List<EngineerDto> getEngineersByBatchId(List<Long> engineerIdList) {
        List<EngineerDto> result = new ArrayList<>();
        for (Long engineerId : engineerIdList) {
            result.add(getEngineerDto(engineerId));
        }
        return result;
    }

    /**
     * 根据工程师Id查询工程师信息
     *
     * @param engineerId 工程师Id
     *
     * @return 返回工程对象
     */
    private EngineerDto getEngineerDto(Long engineerId) {
        EngineerDto engineerDto = new EngineerDto();
        if (StringUtils.isEmpty(engineerId))
            return engineerDto;
        SpcEngineer fx = new SpcEngineer();
        fx.setId(engineerId);
        SpcEngineer spcEngineer = spcEngineerMapper.selectOne(fx);
        if (spcEngineer != null) {
            Long userId = spcEngineer.getUserId();
            UserInfoDto userInfoDto = uacUserFeignApi.getUacUserById(userId).getResult();
            if (userInfoDto != null) {
                try {
                    BeanUtils.copyProperties(engineerDto, spcEngineer);
                    BeanUtils.copyProperties(engineerDto, userInfoDto);
                } catch (Exception e) {
                    logger.error("工程师Dto与用户Dto属性拷贝异常");
                    e.printStackTrace();
                }
            }
        }
        return engineerDto;
    }

    @Override
    public List<EngineerDto> queryAllEngineers(SpcEngineer spcEngineer, LoginAuthDto loginAuthDto) {
        List<EngineerDto> result = new ArrayList<>();
        SpcCompany query = new SpcCompany();
        query.setUserId(loginAuthDto.getUserId());
        SpcCompany spcCompany = spcCompanyMapper.selectOne(query);
        if (PublicUtil.isEmpty(spcCompany)) {
            return result;
        }
        List<Long> engineerIdList = uacGroupFeignApi.getUacUserIdListByGroupId(spcCompany.getGroupId()).getResult();
//        SpcCompanyEngineer spcCompanyEngineer = new SpcCompanyEngineer();
//        spcCompanyEngineer.setCompanyId(spcCompany.getId());
//        List<SpcCompanyEngineer> spcCompanyEngineers = spcCompanyEngineerMapper.select(spcCompanyEngineer);
//        for (SpcCompanyEngineer spcCE : spcCompanyEngineers) {
//            Long engineerId = spcCE.getEngineerId();
//            SpcEngineer queryResult = spcEngineerMapper.selectByPrimaryKey(engineerId);
//            String position = spcEngineer.getPosition();
//            if (!StringUtils.isEmpty(position) && !position.equals(queryResult.getPosition()))
//                continue;
//            result.add(getEngineerDto(engineerId));
//        }
        for(Long engineerId : engineerIdList) {
            SpcEngineer queryResult = spcEngineerMapper.selectByPrimaryKey(engineerId);
            String position = spcEngineer.getPosition();
            if (!StringUtils.isEmpty(position) && !position.equals(queryResult.getPosition()))
                continue;
            result.add(getEngineerDto(engineerId));
        }
        return result;
    }

    @Override
    public List<EngineerDto> queryListWithStatus(EngineerStatusDto engineerStatusDto, LoginAuthDto loginAuthDto) {
        List<EngineerDto> result = new ArrayList<>();
        Integer engineerStatus = engineerStatusDto.getStatus();
        SpcCompany query = new SpcCompany();
        query.setUserId(loginAuthDto.getUserId());
        SpcCompany spcCompany = spcCompanyMapper.selectOne(query);
        if (PublicUtil.isEmpty(spcCompany)) {
            return result;
        }
        List<Long> engineerIdList = uacGroupFeignApi.getUacUserIdListByGroupId(spcCompany.getGroupId()).getResult();
//        SpcCompanyEngineer spcCompanyEngineer = new SpcCompanyEngineer();
//        spcCompanyEngineer.setCompanyId(spcCompany.getId());
//        List<SpcCompanyEngineer> spcCompanyEngineers = spcCompanyEngineerMapper.select(spcCompanyEngineer);
//        for (SpcCompanyEngineer spcCE : spcCompanyEngineers) {
//            EngineerDto engineerDto = getEngineerDto(spcCE.getEngineerId());
//            if (!Integer.valueOf(engineerDto.getStatus()).equals(engineerStatus))
//                continue;
//            result.add(engineerDto);
//        }
        for(Long engineerId : engineerIdList){
            EngineerDto engineerDto = getEngineerDto(engineerId);
            if(!Integer.valueOf(engineerDto.getStatus()).equals(engineerStatus))
                continue;
            result.add(engineerDto);
        }
        return result;
    }

    @Override
    public void addSpcEngineer(EngineerRegisterDto engineerRegisterDto, LoginAuthDto loginAuthDto) {
        Long loginAuthDtoUserId = loginAuthDto.getUserId();
        SpcCompany spcCompany = new SpcCompany();
        spcCompany.setUserId(loginAuthDtoUserId);
//        Long companyId = spcCompanyMapper.selectOne(spcCompany).getId();
        // 校验注册信息
        validateRegisterInfo(engineerRegisterDto);

        UserInfoDto userInfoDto = new UserInfoDto();
        SpcEngineer spcEngineer = new SpcEngineer();
        try {
            BeanUtils.copyProperties(userInfoDto, engineerRegisterDto);
            BeanUtils.copyProperties(spcEngineer, engineerRegisterDto);
        } catch (Exception e) {
            logger.error("addSpcEngineer 工程师Dto与用户Dto属性拷贝异常");
            e.printStackTrace();
        }
        Long newUserId = uacUserFeignApi.addUser(userInfoDto).getResult();
        Long engineerId = super.generateId();
        spcEngineer.setId(engineerId);
        spcEngineer.setUserId(newUserId);
        spcEngineerMapper.insertSelective(spcEngineer);
//        SpcCompanyEngineer spcCompanyEngineer = new SpcCompanyEngineer();
//        spcCompanyEngineer.setCompanyId(companyId);
//        spcCompanyEngineer.setEngineerId(engineerId);
//        spcCompanyEngineerMapper.insert(spcCompanyEngineer);
    }

    private void validateRegisterInfo(EngineerRegisterDto engineerRegisterDto) {
        String mobileNo = engineerRegisterDto.getMobileNo();

        Preconditions.checkArgument(!StringUtils.isEmpty(engineerRegisterDto.getLoginName()), ErrorCodeEnum.UAC10011007.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(engineerRegisterDto.getUserName()), ErrorCodeEnum.SPC100850017.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(mobileNo), "工程师手机号不能为空");
        Preconditions.checkArgument(!StringUtils.isEmpty(engineerRegisterDto.getEmail()), ErrorCodeEnum.UAC10011018.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(engineerRegisterDto.getIdentityNumber()), ErrorCodeEnum.SPC100850018.msg());

    }

    public static List<EngineerRegisterDto> readExcel(InputStream inputStream ,String fileName) throws Exception{
        InputStream is = inputStream;
        Workbook hssfWorkbook = null;
        if (fileName.endsWith("xlsx")){
            hssfWorkbook = new XSSFWorkbook(is);//Excel 2007
        }else if (fileName.endsWith("xls")){
            hssfWorkbook = new HSSFWorkbook(is);//Excel 2003
        }
        //先支持1个sheet
        List<EngineerRegisterDto> list0 = new ArrayList<>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet <hssfWorkbook.getNumberOfSheets(); numSheet++) {
            //HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                //HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                EngineerRegisterDto engineerRegisterDto;
                Row hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    engineerRegisterDto = new EngineerRegisterDto();
                    //HSSFCell name = hssfRow.getCell(0);
                    //HSSFCell pwd = hssfRow.getCell(1);
                    Cell loginName = hssfRow.getCell(0);
                    Cell userName = hssfRow.getCell(1);
                    Cell mobileNum = hssfRow.getCell(2);
                    Cell email = hssfRow.getCell(3);
                    Cell identityNumber = hssfRow.getCell(4);
                    Cell userCode = hssfRow.getCell(5);
                    Cell titleCeNumber = hssfRow.getCell(6);
                    //这里是自己的逻辑
                    engineerRegisterDto.setLoginName(loginName.getStringCellValue());
                    engineerRegisterDto.setUserName(userName.getStringCellValue());
                    engineerRegisterDto.setMobileNo(mobileNum.getStringCellValue());
                    engineerRegisterDto.setEmail(email.getStringCellValue());
                    engineerRegisterDto.setIdentityNumber(identityNumber.getStringCellValue());
                    engineerRegisterDto.setUserCode(userCode.getStringCellValue());
                    engineerRegisterDto.setTitleCeNumber(titleCeNumber.getStringCellValue());
                    list0.add(engineerRegisterDto);
                }
            }
        }
        return list0;
    }

    @Override
    public EngineerVo queryByEngineerId(Long engineerId) {
        logger.info("queryByEngineerId - 根据工程师Id查询工程师信息接口. engineerId={}", engineerId);
        EngineerVo engineerVo = new EngineerVo();
        SpcEngineer queryE = new SpcEngineer();
        queryE.setUserId(engineerId);
        SpcEngineer spcEngineer = spcEngineerMapper.selectOne(queryE);
        Long userId = spcEngineer.getUserId();
        if (!StringUtils.isEmpty(userId)) {
            UserInfoDto userInfoDto = uacUserFeignApi.getUacUserById(userId).getResult();
            try {
                BeanUtils.copyProperties(engineerVo, spcEngineer);
                BeanUtils.copyProperties(engineerVo, userInfoDto);
            } catch (Exception e) {
                logger.error("queryByEngineerId 工程师Dto与用户组Dto属性拷贝异常");
                e.printStackTrace();
            }
        }
        return engineerVo;
    }

    @Override
    public int modifyEngineerStatusById(ModifyEngineerStatusDto modifyEngineerStatusDto) {
        Long engineerId = modifyEngineerStatusDto.getEngineerId();
        String status = modifyEngineerStatusDto.getStatus();
        if (!StringUtils.isEmpty(engineerId) && !StringUtils.isEmpty(status)) {
            Long uacUserId = spcEngineerMapper.selectByPrimaryKey(engineerId).getUserId();
            IdStatusDto modifyUserStatusDto = new IdStatusDto();
            modifyUserStatusDto.setId(uacUserId);
            modifyUserStatusDto.setStatus(Integer.valueOf(status));
            return (int)uacUserFeignApi.modifyUserStatus(modifyUserStatusDto).getResult();
        }
        return 0;
    }

    @Override
    public void saveSpcEngineer(EngineerVo engineerVo, LoginAuthDto loginAuthDto) {
        Long engineerId = engineerVo.getId();
        SpcEngineer queryResutl = spcEngineerMapper.selectByPrimaryKey(engineerId);
        Long userId = queryResutl.getUserId();
        // 校验保存信息
        validateCompanyVo(engineerVo);

        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setId(userId);
        try {
            BeanUtils.copyProperties(userInfoDto, engineerVo);
        } catch (Exception e) {
            logger.error("服务商Dto与用户组Dto属性拷贝异常");
            e.printStackTrace();
        }
        Long uacUserId = uacUserFeignApi.userSave(userInfoDto).getResult();

        if (!StringUtils.isEmpty(engineerId) && !StringUtils.isEmpty(uacUserId)) {
            Date row = new Date();
            // 封装更新公司信息
            SpcEngineer spcEngineer = new SpcEngineer();
            spcEngineer.setId(engineerId);
            spcEngineer.setUserId(userId);
            spcEngineer.setLastOperatorId(loginAuthDto.getUserId());
            spcEngineer.setLastOperator(loginAuthDto.getLoginName());
            try {
                BeanUtils.copyProperties(spcEngineer, engineerVo);
            } catch (Exception e) {
                logger.error("工程师Dto与用户Dto属性拷贝异常");
                e.printStackTrace();
            }
            logger.info("注册工程师. spcEngineer={}", spcEngineer);
            spcEngineerMapper.insertSelective(spcEngineer);
        }
    }

    @Override
    public List<EngineerSimpleVo> getEngineersListByProjectId(Long projectId) {
        List<Long> engineerUserIdList = pmcProjectEngineerFeignApi.getEngineersIdByProjectId(projectId).getResult();
        logger.info("getEngineersIdByProjectId - 根据项目Id查询工程师UserId列表. engineerUserIdList={}", engineerUserIdList);
        List<EngineerSimpleVo> engineerSimpleVos = new ArrayList<>();
        if (engineerUserIdList != null) {
            for (Long userId : engineerUserIdList) {
                UserInfoDto userInfoDto = uacUserFeignApi.getUacUserById(userId).getResult();
                if (userInfoDto != null) {
                    EngineerSimpleVo engineerSimpleVo = new EngineerSimpleVo();
                    engineerSimpleVo.setId(userId);
                    engineerSimpleVo.setName(userInfoDto.getUserName());
                    engineerSimpleVos.add(engineerSimpleVo);
                }
            }
        }
        return engineerSimpleVos;
    }

    private void validateCompanyVo(EngineerVo engineerVo) {
        String mobileNo = engineerVo.getMobileNo();

        Preconditions.checkArgument(!StringUtils.isEmpty(engineerVo.getLoginName()), ErrorCodeEnum.UAC10011007.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(engineerVo.getUserName()), ErrorCodeEnum.SPC100850017.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(mobileNo), "手机号不能为空");
        Preconditions.checkArgument(!StringUtils.isEmpty(engineerVo.getIdentityNumber()), ErrorCodeEnum.SPC100850018.msg());
    }
}
