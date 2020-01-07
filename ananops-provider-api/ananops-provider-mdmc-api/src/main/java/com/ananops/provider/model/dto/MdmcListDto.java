package com.ananops.provider.model.dto;

import com.ananops.provider.model.domain.MdmcTask;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel
public class MdmcListDto implements Serializable {

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("工单列表")
    private List<MdmcTask> taskList;

}
