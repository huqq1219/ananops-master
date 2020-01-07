package com.ananops.provider.model.dto;

import com.ananops.base.dto.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by rongshuai on 2019/12/24 13:07
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class DeviceOrderQueryDto extends BaseQuery {
    private static final long serialVersionUID = 805174746822221645L;
    /**
     * 备品备件对应的巡检任务Id
     */
    @ApiModelProperty(value = "备品备件对应的巡检任务Id")
    private Long taskId;

    /**
     * 备品备件对应的巡检任务子项Id
     */
    @ApiModelProperty(value = "备品备件对应的巡检任务子项Id")
    private Long itemId;

}
