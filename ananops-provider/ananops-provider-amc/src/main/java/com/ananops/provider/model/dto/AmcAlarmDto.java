package com.ananops.provider.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created By ChengHao On 2020/1/6
 */
@Data
@ApiModel
public class AmcAlarmDto implements Serializable {

    private static final long serialVersionUID = -6325475993845118613L;

    @ApiModelProperty(value = "报警id")
    private Long id;
    /**
     * 告警名称
     */
    @ApiModelProperty(value = "告警名称")
    private String alarmName;

    /**
     * 告警类型
     */
    @ApiModelProperty(value = "告警类型")
    private String alarmType;

    /**
     * 告警等级，1-紧急，2-可疑，3-提醒
     */
    @ApiModelProperty(value = "告警等级，1-紧急，2-可疑，3-提醒")
    private Integer alarmLevel;

    /**
     * 受影响资产
     */
    @ApiModelProperty(value = "受影响资产")
    private String alarmAsset;

    /**
     * 受影响资产类型
     */
    @ApiModelProperty(value = "受影响资产类型")
    private String assetType;

    /**
     * 告警状态，1-待处理，0-已处理
     */
    @ApiModelProperty(value = "告警状态，1-待处理，0-已处理")
    private Integer alarmStatus;

    /**
     * 报警位置
     */
    @ApiModelProperty(value = "报警位置")
    private String alarmLocation;

    /**
     * 报警经度
     */
    @ApiModelProperty(value = "报警经度")
    private BigDecimal alarmLongitude;

    /**
     * 报警维度
     */
    @ApiModelProperty(value = "报警维度")
    private BigDecimal alarmLatitude;

    /**
     * 最近发生时间
     */
    @ApiModelProperty(value = "最近发生时间",example = "2019-12-01 12:18:48")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastOccurTime;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;


}
