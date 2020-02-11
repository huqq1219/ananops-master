package com.ananops.provider.model.dto;

import com.ananops.provider.model.domain.MdmcTask;
import com.ananops.provider.model.domain.MdmcTaskItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@ApiModel
@Data
public class MdmcPageItemDto implements Serializable {

    @ApiModelProperty("页码")
    private Integer pageNum;

    @ApiModelProperty("记录数")
    private Integer pageSize;

    @ApiModelProperty("工单列表")
    private List<MdmcTaskItem> taskItemList;
}
