package com.ananops.provider.service;

import com.ananops.base.dto.BaseQuery;
import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.IService;
import com.ananops.provider.model.domain.AmcAlarm;
import com.ananops.provider.model.dto.AlarmQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created By ChengHao On 2020/1/6
 */
public interface AmcAlarmService extends IService<AmcAlarm> {
    /**
     * 编辑报警信息
     * @param amcAlarm
     * @param loginAuthDto
     * @return
     */
    int saveAlarm(AmcAlarm amcAlarm, LoginAuthDto loginAuthDto);

    /**
     * 查询报警信息
     * @param id
     * @return
     */
    AmcAlarm getAlarmById(Long id);

    /**
     * 分页返回用户组织下的报警信息
     * @param baseQuery
     * @return
     */
    PageInfo getAlarmListByGroupId(BaseQuery baseQuery);


    /**
     * 根据告警等级,分页筛选报警信息
     * @param alarmQuery
     * @return
     */
    PageInfo getAlarmListByAlarmLevel(AlarmQuery alarmQuery);

    /**
     * 根据处理情况,分页筛选报警信息
     * @param alarmQuery
     * @return
     */
    PageInfo getAlarmListByAlarmStatus(AlarmQuery alarmQuery);

    /**
     * 获取待处理告警数
     * @return
     */
    int getDealingCount();

    /**
     * 获取急需处理告警数
     * @return
     */
    int getUrgencyCount();

    /**
     * 获取已处理处理告警数
     * @return
     */
    int getDealedCount();

    /**
     * 根据服务商id删除报警信息
     * @param alarmId
     * @return
     */
    int deleteAlarmByAlarmId(Long alarmId);

    /**、
     * 根据告警状态删除报警信息
     * @param alarmStatus
     * @return
     */
    int deleteAlarmsByAlarmStatus(int alarmStatus);

    /**
     * 获取总告警数
     * @return
     */
    int getAllAlarmCount();
}
