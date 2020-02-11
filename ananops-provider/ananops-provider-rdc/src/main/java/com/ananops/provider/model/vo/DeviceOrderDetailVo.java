package com.ananops.provider.model.vo;

import com.ananops.provider.model.domain.Approve;
import lombok.Data;

import java.util.List;

@Data
public class DeviceOrderDetailVo {

    private DeviceOrderVo deviceOrder;

    private Integer approveCount;

    private List<Approve> approves;
}
