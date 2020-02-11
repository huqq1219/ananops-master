package com.ananops.provider.model.vo;

import com.ananops.provider.model.domain.Approve;
import com.ananops.provider.model.domain.DeviceOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;

import java.util.List;

@ApiModel
@Setter
public class ProcessOrderResultVo {
    
    @ApiModelProperty("订单信息")
    private DeviceOrder deviceOrderInfo;
    
    @ApiModelProperty("审核信息")
    private List<Approve> approveInfo;
}
