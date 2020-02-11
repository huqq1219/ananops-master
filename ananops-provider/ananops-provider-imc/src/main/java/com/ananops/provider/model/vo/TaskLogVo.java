package com.ananops.provider.model.vo;

import com.ananops.base.dto.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by rongshuai on 2019/12/12 13:07
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class TaskLogVo extends BaseVo {
    /**
     * 巡检任务的名字
     */
    private String taskName;
    /**
     * 巡检任务的操作
     */
    private String movement;
    /**
     * 操作对应的时间戳
     */
    private Date statusTimestamp;
    /**
     * 操作系统
     */
    private String os;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * ip地址
     */
    private String ipAddress;
    /**
     * 当前任务对应的状态
     */
    private int status;
    /**
     * 当前任务对应的状态名
     */
    private String statusMsg;

}
