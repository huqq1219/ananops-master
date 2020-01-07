package com.ananops.provider.web.rpc;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.BaseController;
import com.ananops.provider.model.domain.UacGroup;
import com.ananops.provider.model.domain.UacUser;
import com.ananops.provider.model.dto.group.GroupSaveDto;
import com.ananops.provider.model.dto.group.GroupStatusDto;
import com.ananops.provider.model.dto.user.IdStatusDto;
import com.ananops.provider.model.dto.user.UserInfoDto;
import com.ananops.provider.model.service.UacGroupFeignApi;
import com.ananops.provider.service.UacGroupService;
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
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 操作Group
 *
 * Created by bingyueduan on 2019/12/29.
 */
@RestController
@Api(value = "API - UacGroupFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UacGroupFeignClient extends BaseController implements UacGroupFeignApi {

    @Resource
    private UacGroupService uacGroupService;

    @Resource
    private UacUserService uacUserService;

    @Override
    @ApiOperation(httpMethod = "POST", value = "编辑用户组")
    public Wrapper<Long> groupSave(@RequestBody GroupSaveDto groupSaveDto) {
        LoginAuthDto loginAuthDto = super.getLoginAuthDto();
        UacGroup uacGroup = new UacGroup();
        try {
            BeanUtils.copyProperties(uacGroup, groupSaveDto);
        } catch (Exception e) {
            logger.error("用户组Dto与用户组状态传输Dto属性拷贝异常");
            e.printStackTrace();
        }
        uacGroupService.saveUacGroup(uacGroup, loginAuthDto);
        UacGroup result = uacGroupService.selectOne(uacGroup);
        return WrapMapper.ok(result.getId());
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "修改Group状态")
    public Wrapper<Integer> modifyGroupStatus(@RequestBody IdStatusDto idStatusDto) {
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        int result = uacGroupService.updateUacGroupStatusById(idStatusDto, loginAuthDto);
        return WrapMapper.ok(result);
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "通过状态查询Group集合")
    public Wrapper<List<GroupSaveDto>> queryListByStatus(@RequestBody GroupStatusDto groupStatusDto) {
        List<GroupSaveDto> result = new ArrayList<>();
        UacGroup uacGroup = new UacGroup();
        try {
            BeanUtils.copyProperties(uacGroup, groupStatusDto);
        } catch (Exception e) {
            logger.error("用户组Dto与用户组状态传输Dto属性拷贝异常");
            e.printStackTrace();
        }
        List<UacGroup> groups = uacGroupService.select(uacGroup);
        for (UacGroup uacGroupT : groups) {
            GroupSaveDto groupSaveDto = new GroupSaveDto();
            try {
                BeanUtils.copyProperties(groupSaveDto, uacGroupT);
            } catch (Exception e) {
                logger.error("用户组Dto与用户组传输Dto属性拷贝异常");
                e.printStackTrace();
            }
            result.add(groupSaveDto);
        }
        return WrapMapper.ok(result);
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "通过Id查询Group信息")
    public Wrapper<GroupSaveDto> getUacGroupById(Long groupId) {
        logger.info("根据组织Id查询组织列表");
        GroupSaveDto groupSaveDto = new GroupSaveDto();
        UacGroup uacGroup = uacGroupService.queryById(groupId);
        try {
            BeanUtils.copyProperties(groupSaveDto, uacGroup);
        } catch (Exception e) {
            logger.error("用户组Dto与用户组传输Dto属性拷贝异常");
            e.printStackTrace();
        }
        return WrapMapper.ok(groupSaveDto);
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "根据Group的Id查询对应的全部User的Id")
    public Wrapper<List<Long>> getUacUserIdListByGroupId(@RequestParam("groupId")Long groupId){
        logger.info("根据组织Id查询组织对应的全部用户的Id");
        Example example = new Example(UacUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("groupId",groupId);
        List<Long> userIdList = new ArrayList<>();
        List<UacUser> uacUserList = uacUserService.selectByExample(example);
        uacUserList.forEach(uacUser -> {
            Long userId = uacUser.getId();
            userIdList.add(userId);
        });
        return WrapMapper.ok(userIdList);
    }
}
