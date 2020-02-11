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
public class ItemLogVo extends BaseVo {
    private static final long serialVersionUID = 8450744666343412025L;
    /**
     * 巡检任务子项对应的巡检任务名字
     */
    private String taskName;
    /**
     * 巡检任务子项的名字
     */
    private String itemName;
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
     * 当前任务子项对应的状态
     */
    private int status;
    /**
     * 当前任务子项对应的状态名
     */
    private String statusMsg;
}
