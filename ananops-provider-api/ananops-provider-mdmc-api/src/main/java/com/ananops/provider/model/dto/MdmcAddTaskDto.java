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

    @ApiModelProperty("维修任务下一状态")
    private Integer status;

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

    @ApiModelProperty("工程师ID")
    private Long maintainerId;

    @ApiModelProperty("当前花费")
    private BigDecimal totalCost;

    @ApiModelProperty("紧急程度")
    private Integer level;

    @ApiModelProperty("预约时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date appointTime;

    @ApiModelProperty("最晚维修时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deadline;

    @ApiModelProperty("实际开始维修时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date scheduledStartTime;

    @ApiModelProperty("实际结束维修时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date scheduledFinishTime;

    @ApiModelProperty("报修地址（经度）")
    private BigDecimal requestLatitude;

    @ApiModelProperty("报修地址（纬度）")
    private BigDecimal requestLongitude;

    @ApiModelProperty("报修地址（小程序报修要转换成字符串地址填入）")
    private String addressName;

    @ApiModelProperty("维修结果")
    private Integer result;

    @ApiModelProperty("维修建议")
    private String suggestion;

    @ApiModelProperty("报修人电话")
    private String call;

    @ApiModelProperty("备注")
    private String note;

    @ApiModelProperty("维修工单类型（维修填1，巡检填2）")
    private Integer objectType;

    @ApiModelProperty("维修类型不填，巡检类型填巡检工单编号")
    private Long objectId;

    @ApiModelProperty("任务子项")
    private List<MdmcAddTaskItemDto> mdmcAddTaskItemDtoList;
}
