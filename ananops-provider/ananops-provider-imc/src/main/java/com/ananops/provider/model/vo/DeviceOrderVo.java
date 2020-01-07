package com.ananops.provider.model.vo;

import com.ananops.base.dto.BaseVo;
import com.ananops.provider.model.domain.ImcDevice;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import java.util.List;

/**
 * Created by rongshuai on 2020/1/4 9:44
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class DeviceOrderVo extends BaseVo{
    private static final long serialVersionUID = -1141938758339969103L;

    /**
     * 对应的巡检子项目的ID
     */
    private Long inspectionItemId;

    /**
     * 对应的巡检任务的ID
     */
    private Long inspectionTaskId;

    /**
     * 当前备品备件订单的处理状态
     */
    private Integer status;

    /**
     * 对该订单的处理意见
     */
    private String comment;

    /**
     * 订单对应的备品备件列表
     */
    private List<ImcDevice> imcDeviceList;
}
