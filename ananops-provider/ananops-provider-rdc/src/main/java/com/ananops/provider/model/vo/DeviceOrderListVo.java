package com.ananops.provider.model.vo;

import com.ananops.provider.model.domain.Approve;
import lombok.Data;

import java.util.List;

@Data
public class DeviceOrderListVo {

    private Integer deviceOrderCount;

    private List<DeviceOrderDetailVo> deviceOrderList;


}
