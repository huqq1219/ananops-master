package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DeviceDto {

    @ApiModelProperty(value = "备品备件id")
    private Long deviceId;

    @ApiModelProperty(value = "备品备件数量")
    private int nums;
}
