package com.ananops.provider.model.dto;

import com.ananops.base.dto.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created By ChengHao On 2020/1/6
 */
@Data
@ApiModel
public class AlarmQuery extends BaseQuery {

    /**
     * 告警等级，1-紧急，2-可疑，3-提醒
     */
    @ApiModelProperty(value = "告警等级，1-紧急，2-可疑，3-提醒")
    private Integer alarmLevel;

    /**
     * 告警状态，1-待处理，0-已处理
     */
    @ApiModelProperty(value = "告警状态，1-待处理，0-已处理")
    private Integer alarmStatus;
}
