package com.ananops.provider.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 工单
 * Created By ChengHao On 2020/1/7
 */
@Data
@ApiModel
public class AmcAddTaskDto implements Serializable {
    private static final long serialVersionUID = 1441898339708050619L;

    @ApiModelProperty(value = "项目id")
    private Long projectId;

    @ApiModelProperty("维修任务名称")
    private String title;

    @ApiModelProperty(value = "预约时间",example = "2019-12-01 12:18:48")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date appointTime;

    @ApiModelProperty("紧急程度")
    private Integer level;

    @ApiModelProperty("报修人电话")
    private String call;

    @ApiModelProperty("任务子项")

    private List<MdmcAddTaskItemDto> mdmcAddTaskItemDtoList;

}
