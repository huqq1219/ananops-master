package com.ananops.provider.model.service;

import com.ananops.provider.model.dto.user.IdStatusDto;
import com.ananops.provider.model.dto.user.UserInfoDto;
import com.ananops.provider.model.dto.user.UserRegisterDto;
import com.ananops.provider.model.service.hystrix.UacUserFeignHystrix;
import com.ananops.security.feign.OAuth2FeignAutoConfiguration;
import com.ananops.wrapper.Wrapper;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 开放内部模块对UAC User的API
 *
 * Created by bingyueduan on 2019/12/29.
 */
@FeignClient(value = "ananops-provider-uac", configuration = OAuth2FeignAutoConfiguration.class, fallback = UacUserFeignHystrix.class)
public interface UacUserFeignApi {

    /**
     * 在UAC中注册用户.
     *
     * @return the wrapper
     */
    @PostMapping(value = "/api/uac/user/register")
    Wrapper<Long> userRegister(@RequestBody UserRegisterDto userRegisterDto);

    /**
     * 根据UserId获取用户信息
     *
     * @param userId
     *
     * @return
     */
    @PostMapping(value = "/api/uac/user/getUacUserById")
    Wrapper<UserInfoDto> getUacUserById(@RequestParam("userId") Long userId);

    /**
     * 在UAC中注册添加用户,设置默认密码及状态.
     *
     * @return the wrapper
     */
    @PostMapping(value = "/api/uac/user/add")
    Wrapper<Long> addUser(@RequestBody UserInfoDto userInfoDto);

    /**
     * 更改UAC中User的状态.
     *
     * @return the wrapper
     */
    @PostMapping(value = "/api/uac/user/modifyUserStatusById")
    Wrapper<Integer> modifyUserStatus(IdStatusDto modifyUserStatusDto);

    /**
     * 在UAC中更新用户.
     *
     * @return the wrapper
     */
    @PostMapping(value = "/api/uac/user/save")
    Wrapper<Long> userSave(@RequestBody UserInfoDto userInfoDto);
}
