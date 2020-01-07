package com.ananops.provider.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@ApiModel
public class MdmcAddTaskDto implements Serializable {
    private static final long serialVersionUID = 7339286966929037187L;

    @ApiModelProperty("维修任务ID")
    private Long id;

    @ApiModelProperty("维修任务名称")
    private String title;

    @ApiModelProperty("审核人ID")
    private Long principalId;

    @ApiModelProperty("项目ID")
    private Long projectId;

    @ApiModelProperty("合同id")
    private Long contractId;

    @ApiModelProperty("服务商ID")
    private Long facilitatorId;

    @ApiModelProperty("报修人ID")
    private Long userId;

    @ApiModelProperty("当前花费")
    private BigDecimal totalCost;

    @ApiModelProperty("紧急程度")
    private Integer level;

    @ApiModelProperty("预约时间")
    private Date appointTime;

    @ApiModelProperty("维修结果")
    private Integer result;

    @ApiModelProperty("维修建议")
    private String suggestion;

    @ApiModelProperty("报修人电话")
    private String call;

    @ApiModelProperty("任务子项")
    private List<MdmcAddTaskItemDto> mdmcAddTaskItemDtoList;
}
