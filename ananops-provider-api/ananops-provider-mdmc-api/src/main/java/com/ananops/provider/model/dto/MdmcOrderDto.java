package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * @author zhs
 */

@Data
@ApiModel
public class MdmcOrderDto implements Serializable {
    private static final long serialVersionUID = 7339286966929037187L;

    @ApiModelProperty("工单ID")
    private Long uId;

    @ApiModelProperty("维修任务名称")
    private String title;

    @ApiModelProperty("审核人ID")
    private Long principalId;

    @ApiModelProperty("项目ID")
    private Long projectId;

    @ApiModelProperty("合同id")
    private Long contractId;

    @ApiModelProperty("地址名")
    private String addressName;

    @ApiModelProperty("报修人姓名")
    private String creator;

    @ApiModelProperty("服务商ID")
    private Long facilitatorId;

    @ApiModelProperty("报修人ID")
    private Long userId;

    @ApiModelProperty("报修人电话")
    private String creator_call;

//    @ApiModelProperty("设备地址-经度")
//    private BigDecimal latitude;
//
//    @ApiModelProperty("设备地址-纬度")
//    private BigDecimal longitude;

    @ApiModelProperty("总花费")
    private BigDecimal totalCost;

    @ApiModelProperty("支付方式")
    private Integer payMode;

    @ApiModelProperty("任务子项")
    private List<MdmcTaskItemDto> taskItemDtoList;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("报修时间")
    private Date created_time;

    @ApiModelProperty("紧急程度")
    private Integer level;

    @ApiModelProperty("预约时间")
    private Date appoint_time;

}
