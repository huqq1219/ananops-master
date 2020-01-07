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
import com.ananops.provider.service.AmcAlarmService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created By ChengHao On 2020/1/6
 */
@Slf4j
@Service
public class AmcAlarmServiceImpl extends BaseService<AmcAlarm> implements AmcAlarmService {
    @Resource
    AmcAlarmMapper amcAlarmMapper;

    @Override
    public int saveAlarm(AmcAlarm amcAlarm, LoginAuthDto loginAuthDto) {
        int result = 0;
        amcAlarm.setUpdateInfo(loginAuthDto);
        if (amcAlarm.isNew()) {
            amcAlarm.setId(super.generateId());
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
        Example example = new Example(AmcAlarm.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("groupId", loginAuthDto.getGroupId());
        List<AmcAlarm> amcAlarmList = amcAlarmMapper.selectByExample(example);
        return new PageInfo<>(amcAlarmList);
    }

    @Override
    public PageInfo getAlarmListByProjectId(AlarmQuery alarmQuery) {
        PageHelper.startPage(alarmQuery.getPageNum(), alarmQuery.getPageSize());
        Example example = new Example(AmcAlarm.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", alarmQuery.getId());
        List<AmcAlarm> amcAlarmList = amcAlarmMapper.selectByExample(example);
        return new PageInfo<>(amcAlarmList);
    }



    @Override
    public PageInfo getAlarmListByAlarmLevel(AlarmQuery alarmQuery) {
        PageHelper.startPage(alarmQuery.getPageNum(), alarmQuery.getPageSize());
        Example example = new Example(AmcAlarm.class);
        Example.Criteria criteria = example.createCriteria();
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        criteria.andEqualTo("groupId", loginAuthDto.getGroupId());
        criteria.andEqualTo("alarmLevel", alarmQuery.getAlarmLevel());
        List<AmcAlarm> amcAlarmList = amcAlarmMapper.selectByExample(example);
        return new PageInfo<>(amcAlarmList);
    }

    @Override
    public PageInfo getAlarmListByAlarmStatus(AlarmQuery alarmQuery) {
        PageHelper.startPage(alarmQuery.getPageNum(), alarmQuery.getPageSize());
        Example example = new Example(AmcAlarm.class);
        Example.Criteria criteria = example.createCriteria();
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        criteria.andEqualTo("groupId", loginAuthDto.getGroupId());
        criteria.andEqualTo("alarmStatus", alarmQuery.getAlarmStatus());
        List<AmcAlarm> amcAlarmList = amcAlarmMapper.selectByExample(example);
        return new PageInfo<>(amcAlarmList);
    }

    @Override
    public int getDealingCount() {
        Example example = new Example(AmcAlarm.class);
        Example.Criteria criteria = example.createCriteria();
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        criteria.andEqualTo("groupId", loginAuthDto.getGroupId());
        criteria.andEqualTo("alarmStatus", 1);
        return amcAlarmMapper.selectCountByExample(example);
    }

    @Override
    public int getUrgencyCount() {
        Example example = new Example(AmcAlarm.class);
        Example.Criteria criteria = example.createCriteria();
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        criteria.andEqualTo("groupId", loginAuthDto.getGroupId());
        criteria.andEqualTo("alarmLevel", 1);
        return amcAlarmMapper.selectCountByExample(example);
    }

    @Override
    public int getDealedCount() {
        Example example = new Example(AmcAlarm.class);
        Example.Criteria criteria = example.createCriteria();
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        criteria.andEqualTo("groupId", loginAuthDto.getGroupId());
        criteria.andEqualTo("alarmStatus", 0);
        return amcAlarmMapper.selectCountByExample(example);
    }



}
