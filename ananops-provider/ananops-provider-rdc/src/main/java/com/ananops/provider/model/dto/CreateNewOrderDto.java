package com.ananops.provider.model.dto;

import com.alibaba.fastjson.JSONObject;
import com.ananops.provider.model.domain.Device;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel
public class CreateNewOrderDto implements Serializable {
    private static final long serialVersionUID = -8803930240594849208L;
    
    @ApiModelProperty("备品备件订单绑定的对象类型")
    private Integer objectType;
    
    @ApiModelProperty("备品备件订单绑定的对象编号")
    private Long objectId;
    
    @ApiModelProperty("备品备件订单申请人编号")
    private Long applicantId;
    
    @ApiModelProperty("备品备件订单申请人")
    private String applicant;
    
    @ApiModelProperty("备品备件订单审批人编号")
    private Long currentApproverId;
    
    @ApiModelProperty("备品备件订单审批人")
    private String currentApprover;
    
    @ApiModelProperty("备品备件订单设备详情")
    private List<DeviceOrderItemInfoDto> items;
}
