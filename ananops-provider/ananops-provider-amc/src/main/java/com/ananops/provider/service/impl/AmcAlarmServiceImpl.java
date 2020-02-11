package com.ananops.provider.service.impl;

import com.ananops.base.dto.BaseQuery;
import com.ananops.base.dto.LoginAuthDto;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.core.support.BaseService;
import com.ananops.core.utils.RequestUtil;
import com.ananops.provider.exception.AmcBizException;
import com.ananops.provider.mapper.AmcAlarmMapper;
import com.ananops.provider.model.domain.AmcAlarm;
import com.ananops.provider.model.dto.AlarmQuery;
import com.ananops.provider.model.service.UacGroupFeignApi;
import com.ananops.provider.model.vo.GroupZtreeVo;
import com.ananops.provider.service.AmcAlarmService;
import com.ananops.wrapper.Wrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By ChengHao On 2020/1/6
 */
@Slf4j
@Service
public class AmcAlarmServiceImpl extends BaseService<AmcAlarm> implements AmcAlarmService {
    @Resource
    AmcAlarmMapper amcAlarmMapper;
    @Resource
    UacGroupFeignApi uacGroupFeignApi;

    @Override
    public int saveAlarm(AmcAlarm amcAlarm, LoginAuthDto loginAuthDto) {
        int result = 0;
        amcAlarm.setUpdateInfo(loginAuthDto);
        if (amcAlarm.isNew()) {
            amcAlarm.setId(super.generateId());
            amcAlarm.setAlarmStatus(1);
            amcAlarm.setGroupId(loginAuthDto.getGroupId());
            amcAlarm.setGroupName(loginAuthDto.getGroupName());
            result = amcAlarmMapper.insertSelective(amcAlarm);
        } else {
            result = amcAlarmMapper.updateByPrimaryKeySelective(amcAlarm);
            if (result < 1) {
                throw new AmcBizException(ErrorCodeEnum.AMC10091011, amcAlarm.getId());
            }
        }
        return result;
    }

    @Override
    public AmcAlarm getAlarmById(Long id) {
        return amcAlarmMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo getAlarmListByGroupId(BaseQuery baseQuery) {
        PageHelper.startPage(baseQuery.getPageNum(), baseQuery.getPageSize());
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        Wrapper<List<GroupZtreeVo>> wrapper = uacGroupFeignApi.getGroupTreeById(loginAuthDto.getGroupId());
        Example example = new Example(AmcAlarm.class);
        for (int i = 0; i < wrapper.getResult().size(); i++) {
            example.or(example.createCriteria().andEqualTo("groupId",wrapper.getResult().get(i).getId()));
        }
        List<AmcAlarm> amcAlarmList = amcAlarmMapper.selectByExample(example);
        return new PageInfo<>(amcAlarmList);
    }

    @Override
    public PageInfo getAlarmListByAlarmLevel(AlarmQuery alarmQuery) {
        PageHelper.startPage(alarmQuery.getPageNum(), alarmQuery.getPageSize());
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        Wrapper<List<GroupZtreeVo>> wrapper = uacGroupFeignApi.getGroupTreeById(loginAuthDto.getGroupId());
        Example example = new Example(AmcAlarm.class);
        List<Example.Criteria> list = new ArrayList<>();
        for (int i = 0; i < wrapper.getResult().size(); i++) {
            list.add(example.createCriteria());
            list.get(i).andEqualTo("groupId",wrapper.getResult().get(i).getId());
            list.get(i).andEqualTo("alarmLevel", alarmQuery.getAlarmLevel());
            example.or(list.get(i));
        }
        List<AmcAlarm> amcAlarmList = amcAlarmMapper.selectByExample(example);
        return new PageInfo<>(amcAlarmList);
    }

    @Override
    public PageInfo getAlarmListByAlarmStatus(AlarmQuery alarmQuery) {
        PageHelper.startPage(alarmQuery.getPageNum(), alarmQuery.getPageSize());
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        Wrapper<List<GroupZtreeVo>> wrapper = uacGroupFeignApi.getGroupTreeById(loginAuthDto.getGroupId());
        Example example = new Example(AmcAlarm.class);
        List<Example.Criteria> list = new ArrayList<>();
        for (int i = 0; i < wrapper.getResult().size(); i++) {
            list.add(example.createCriteria());
            list.get(i).andEqualTo("groupId",wrapper.getResult().get(i).getId());
            list.get(i).andEqualTo("alarmStatus", alarmQuery.getAlarmStatus());
            example.or(list.get(i));
        }
        List<AmcAlarm> amcAlarmList = amcAlarmMapper.selectByExample(example);
        return new PageInfo<>(amcAlarmList);
    }

    @Override
    public int getAllAlarmCount() {
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        Wrapper<List<GroupZtreeVo>> wrapper = uacGroupFeignApi.getGroupTreeById(loginAuthDto.getGroupId());
        Example example = new Example(AmcAlarm.class);
        for (int i = 0; i < wrapper.getResult().size(); i++) {
            example.or(example.createCriteria().andEqualTo("groupId",wrapper.getResult().get(i).getId()));
        }
        return amcAlarmMapper.selectCountByExample(example);
    }

    @Override
    public int getDealingCount() {
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        Wrapper<List<GroupZtreeVo>> wrapper = uacGroupFeignApi.getGroupTreeById(loginAuthDto.getGroupId());
        Example example = new Example(AmcAlarm.class);
        List<Example.Criteria> list = new ArrayList<>();
        for (int i = 0; i < wrapper.getResult().size(); i++) {
            list.add(example.createCriteria());
            list.get(i).andEqualTo("groupId",wrapper.getResult().get(i).getId());
            list.get(i).andEqualTo("alarmStatus", 1);
            example.or(list.get(i));
        }
        return amcAlarmMapper.selectCountByExample(example);
    }

    @Override
    public int getUrgencyCount() {
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        Wrapper<List<GroupZtreeVo>> wrapper = uacGroupFeignApi.getGroupTreeById(loginAuthDto.getGroupId());
        Example example = new Example(AmcAlarm.class);
        List<Example.Criteria> list = new ArrayList<>();
        for (int i = 0; i < wrapper.getResult().size(); i++) {
            list.add(example.createCriteria());
            list.get(i).andEqualTo("groupId",wrapper.getResult().get(i).getId());
            list.get(i).andEqualTo("alarmLevel", 1);
            example.or(list.get(i));
        }
        return amcAlarmMapper.selectCountByExample(example);
    }

    @Override
    public int getDealedCount() {
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        Wrapper<List<GroupZtreeVo>> wrapper = uacGroupFeignApi.getGroupTreeById(loginAuthDto.getGroupId());
        Example example = new Example(AmcAlarm.class);
        List<Example.Criteria> list = new ArrayList<>();
        for (int i = 0; i < wrapper.getResult().size(); i++) {
            list.add(example.createCriteria());
            list.get(i).andEqualTo("groupId",wrapper.getResult().get(i).getId());
            list.get(i).andEqualTo("alarmStatus", 0);
            example.or(list.get(i));
        }
        return amcAlarmMapper.selectCountByExample(example);
    }

    @Override
    public int deleteAlarmByAlarmId(Long alarmId) {
        return amcAlarmMapper.deleteByPrimaryKey(alarmId);
    }

    @Override
    public int deleteAlarmsByAlarmStatus(int alarmStatus) {
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        Wrapper<List<GroupZtreeVo>> wrapper = uacGroupFeignApi.getGroupTreeById(loginAuthDto.getGroupId());
        Example example = new Example(AmcAlarm.class);
        List<Example.Criteria> list = new ArrayList<>();
        for (int i = 0; i < wrapper.getResult().size(); i++) {
            list.add(example.createCriteria());
            list.get(i).andEqualTo("groupId",wrapper.getResult().get(i).getId());
            list.get(i).andEqualTo("alarmStatus", alarmStatus);
            example.or(list.get(i));
        }
        return amcAlarmMapper.deleteByExample(example);
    }



}
