package com.ananops.provider.web.frontend;

import com.ananops.base.dto.BaseQuery;
import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.BaseController;
import com.ananops.provider.model.domain.AmcAlarm;
import com.ananops.provider.model.dto.AlarmQuery;
import com.ananops.provider.model.dto.AmcAlarmDto;
import com.ananops.provider.service.AmcAlarmService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created By ChengHao On 2020/1/6
 */
@RestController
@RequestMapping(value = "/alarm", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
@Api(value = "WEB -AmcAlarmController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AmcAlarmController extends BaseController {
    @Resource
    AmcAlarmService amcAlarmService;

    @PostMapping("/save")
    @ApiOperation(httpMethod = "POST", value = "编辑报警信息,当id为空时新增报警,不为空时为更新报警信息")
    public Wrapper saveContract(@RequestBody AmcAlarmDto amcAlarmDto) {
        int result = 0;
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        AmcAlarm amcAlarm = new AmcAlarm();
        BeanUtils.copyProperties(amcAlarmDto, amcAlarm);
        result = amcAlarmService.saveAlarm(amcAlarm, loginAuthDto);
        return WrapMapper.ok();
    }

    @PostMapping("/getAlarmById/{id}")
    @ApiOperation(httpMethod = "POST", value = "根据报警id,查看报警信息")
    public Wrapper<AmcAlarm> getAlarmById(@PathVariable Long id) {
        AmcAlarm amcAlarm = amcAlarmService.getAlarmById(id);
        return WrapMapper.ok(amcAlarm);
    }

    @PostMapping("/getAlarmListByGroupId")
    @ApiOperation(httpMethod = "POST", value = "分页返回用户组织下的报警信息")
    public Wrapper<PageInfo> getAlarmListByGroupId(@RequestBody BaseQuery baseQuery) {
        PageInfo pageInfo = amcAlarmService.getAlarmListByGroupId(baseQuery);
        return WrapMapper.ok(pageInfo);
    }

    @PostMapping("/getAlarmListByProjectId")
    @ApiOperation(httpMethod = "POST", value = "根据项目id,分页筛选报警信息")
    public Wrapper<PageInfo> getAlarmListByProjectId(@RequestBody AlarmQuery alarmQuery) {
        PageInfo pageInfo = amcAlarmService.getAlarmListByProjectId(alarmQuery);
        return WrapMapper.ok(pageInfo);
    }

    @PostMapping("/getAlarmListByAlarmLevel")
    @ApiOperation(httpMethod = "POST", value = "根据告警等级,分页筛选报警信息")
    public Wrapper<PageInfo> getAlarmListByAlarmLevel(@RequestBody AlarmQuery alarmQuery) {
        PageInfo pageInfo = amcAlarmService.getAlarmListByAlarmLevel(alarmQuery);
        return WrapMapper.ok(pageInfo);
    }

    @PostMapping("/getAlarmListByAlarmStatus")
    @ApiOperation(httpMethod = "POST", value = "根据处理情况,分页筛选报警信息")
    public Wrapper<PageInfo> getAlarmListByAlarmStatus(@RequestBody AlarmQuery alarmQuery) {
        PageInfo pageInfo = amcAlarmService.getAlarmListByAlarmStatus(alarmQuery);
        return WrapMapper.ok(pageInfo);
    }

    @PostMapping("/getDealingCount")
    @ApiOperation(httpMethod = "POST", value = "获取待处理告警数")
    public Wrapper getDealingCount() {
        int count = 0;
        count = amcAlarmService.getDealingCount();
        return WrapMapper.ok(count);
    }

    @PostMapping("getUrgencyCount")
    @ApiOperation(httpMethod = "POST", value = "获取急需处理告警数")
    public Wrapper getUrgencyCount() {
        int count = 0;
        count = amcAlarmService.getUrgencyCount();
        return WrapMapper.ok(count);
    }

    @PostMapping("/getDealedCount")
    @ApiOperation(httpMethod = "POST", value = "获取已处理告警数")
    public Wrapper getDealedCount() {
        int count = 0;
        count = amcAlarmService.getDealedCount();
        return WrapMapper.ok(count);
    }

}
