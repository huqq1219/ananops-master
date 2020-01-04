package com.ananops.provider.model.service.hystrix;

import com.ananops.provider.model.dto.user.IdStatusDto;
import com.ananops.provider.model.dto.user.UserInfoDto;
import com.ananops.provider.model.dto.user.UserRegisterDto;
import com.ananops.provider.model.service.UacUserFeignApi;
import com.ananops.wrapper.Wrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * UAC模块User Feign Hystrix
 *
 * Created by bingyueduan on 2019/12/29.
 */
@Component
public class UacUserFeignHystrix implements UacUserFeignApi {

    @Override
    public Wrapper<Long> userRegister(@RequestBody UserRegisterDto userRegisterDto) {
        return null;
    }

    @Override
    public Wrapper<UserInfoDto> getUacUserById(@RequestParam("userId") Long userId) {
        return null;
    }

    @Override
    public Wrapper<Long> addUser(@RequestBody UserInfoDto userInfoDto) {
        return null;
    }

    @Override
    public Wrapper<Integer> modifyUserStatus(IdStatusDto modifyUserStatusDto) {
        return null;
    }

    @Override
    public Wrapper<Long> userSave(@RequestBody UserInfoDto userInfoDto) {
        return null;
    }
}
