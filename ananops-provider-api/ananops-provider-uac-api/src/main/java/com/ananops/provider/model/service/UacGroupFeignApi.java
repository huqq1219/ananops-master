package com.ananops.provider.model.service;

import com.ananops.provider.model.dto.group.GroupSaveDto;
import com.ananops.provider.model.dto.group.GroupStatusDto;
import com.ananops.provider.model.dto.user.IdStatusDto;
import com.ananops.provider.model.service.hystrix.UacGroupFeignHystrix;
import com.ananops.security.feign.OAuth2FeignAutoConfiguration;
import com.ananops.wrapper.Wrapper;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 开放内部模块对UAC Group的API
 *
 * Created by bingyueduan on 2019/12/29.
 */
@FeignClient(value = "ananops-provider-uac", configuration = OAuth2FeignAutoConfiguration.class, fallback = UacGroupFeignHystrix.class)
public interface UacGroupFeignApi {

    /**
     * 在UAC中注册用户.
     *
     * @return the wrapper
     */
    @PostMapping(value = "/api/uac/group/save")
    Wrapper<Long> groupSave(@RequestBody GroupSaveDto groupSaveDto);

    /**
     * 更改UAC中Group的状态.
     *
     * @return the wrapper
     */
    @PostMapping(value = "/api/uac/group/modifyGroupStatusById")
    Wrapper<Integer> modifyGroupStatus(@RequestBody IdStatusDto idStatusDto);

    /**
     * 根据Group状态查询Group列表.
     *
     * @return the wrapper
     */
    @PostMapping(value = "/api/uac/group/queryListByStatus")
    Wrapper<List<GroupSaveDto>> queryListByStatus(@RequestBody GroupStatusDto groupStatusDto);

    /**
     * 根据Group Id查询Group信息.
     *
     * @return the wrapper
     */
    @PostMapping(value = "/api/uac/group/getUacGroupById")
    Wrapper<GroupSaveDto> getUacGroupById(Long groupId);
}
