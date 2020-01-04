package com.ananops.provider.web.rpc;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.BaseController;
import com.ananops.provider.model.domain.UacRole;
import com.ananops.provider.model.domain.UacUser;
import com.ananops.provider.model.dto.user.IdStatusDto;
import com.ananops.provider.model.dto.user.UserInfoDto;
import com.ananops.provider.model.dto.user.UserRegisterDto;
import com.ananops.provider.model.service.UacUserFeignApi;
import com.ananops.provider.service.UacRoleService;
import com.ananops.provider.service.UacUserService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * 操作User
 *
 * Created by bingyueduan on 2019/12/29.
 */
@RestController
@Api(value = "API - UacUserFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UacUserFeignClient extends BaseController implements UacUserFeignApi {

    @Resource
    private UacUserService uacUserService;

    @Resource
    private UacRoleService uacRoleService;

    @Override
    @ApiOperation(httpMethod = "POST", value = "注册用户")
    public Wrapper<Long> userRegister(@RequestBody UserRegisterDto userRegisterDto) {
        uacUserService.register(userRegisterDto);
        UacUser uacUser = new UacUser();
        uacUser.setLoginName(userRegisterDto.getLoginName());
        uacUser.setMobileNo(userRegisterDto.getMobileNo());
        uacUser.setEmail(userRegisterDto.getEmail());
        UacUser result = uacUserService.selectOne(uacUser);
        return WrapMapper.ok(result.getId());
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "根据用户Id查询用户信息")
    public Wrapper<UserInfoDto> getUacUserById(@RequestParam("userId") Long userId) {
        UserInfoDto userInfoDto = new UserInfoDto();
        logger.info("getUacUserById - 根据用户Id查询用户信息. userId={}", userId);
        UacUser uacUser = uacUserService.queryByUserId(userId);
        List<UacRole> roleList = uacRoleService.findAllRoleInfoByUserId(userId);
        Iterator<UacRole> iterator = roleList.iterator();
        if (iterator.hasNext()) {
            UacRole uacRole = iterator.next();
            userInfoDto.setRoleId(uacRole.getId());
            userInfoDto.setRoleName(uacRole.getRoleName());
            userInfoDto.setRoleCode(uacRole.getRoleCode());
            userInfoDto.setRoleStatus(uacRole.getStatus());
        }
        logger.info("getUacUserById - 根据用户Id查询用户信息. [OK] uacUser={}", uacUser);
        try {
            BeanUtils.copyProperties(userInfoDto, uacUser);
        } catch (Exception e) {
            logger.error("服务商Dto与用户Dto属性拷贝异常");
            e.printStackTrace();
        }
        return WrapMapper.ok(userInfoDto);
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "添加用户")
    public Wrapper<Long> addUser(@RequestBody UserInfoDto userInfoDto) {
        uacUserService.addUser(userInfoDto);
        UacUser uacUser = new UacUser();
        uacUser.setLoginName(userInfoDto.getLoginName());
        uacUser.setMobileNo(userInfoDto.getMobileNo());
        uacUser.setEmail(userInfoDto.getEmail());
        UacUser result = uacUserService.selectOne(uacUser);
        return WrapMapper.ok(result.getId());
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "根据用户Id修改用户状态")
    public Wrapper<Integer> modifyUserStatus(IdStatusDto idStatusDto) {
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        UacUser uacUser = new UacUser();
        uacUser.setId(idStatusDto.getId());
        int result = uacUserService.modifyUserStatusById(uacUser, loginAuthDto);
        return WrapMapper.ok(result);
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "更新用户信息")
    public Wrapper<Long> userSave(@RequestBody UserInfoDto userInfoDto) {
        LoginAuthDto loginAuthDto = super.getLoginAuthDto();
        UacUser uacUser = new UacUser();
        try {
            BeanUtils.copyProperties(uacUser, userInfoDto);
        } catch (Exception e) {
            logger.error("用户Dto与用户传输Dto属性拷贝异常");
            e.printStackTrace();
        }
        uacUserService.saveUacUser(uacUser, loginAuthDto);
        UacUser result = uacUserService.selectOne(uacUser);
        return WrapMapper.ok(result.getId());
    }
}
