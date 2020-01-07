package com.ananops.provider.model.service.hystrix;

import com.ananops.provider.model.dto.group.GroupSaveDto;
import com.ananops.provider.model.dto.group.GroupStatusDto;
import com.ananops.provider.model.dto.user.IdStatusDto;
import com.ananops.provider.model.service.UacGroupFeignApi;
import com.ananops.wrapper.Wrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * UAC模块Group Feign Hystrix
 *
 * Created by bingyueduan on 2019/12/29.
 */
@Component
public class UacGroupFeignHystrix implements UacGroupFeignApi {

    @Override
    public Wrapper<Long> groupSave(@RequestBody GroupSaveDto groupSaveDto) {
        return null;
    }

    @Override
    public Wrapper<Integer> modifyGroupStatus(@RequestBody IdStatusDto idStatusDto) {
        return null;
    }

    @Override
    public Wrapper<List<GroupSaveDto>> queryListByStatus(@RequestBody GroupStatusDto groupStatusDto) {
        return null;
    }

    @Override
    public Wrapper<GroupSaveDto> getUacGroupById(@RequestParam("groupId") Long groupId) {
        return null;
    }

    @Override
    public Wrapper<List<Long>> getUacUserIdListByGroupId(@RequestParam("groupId")Long groupId){
        return null;
    }
}
