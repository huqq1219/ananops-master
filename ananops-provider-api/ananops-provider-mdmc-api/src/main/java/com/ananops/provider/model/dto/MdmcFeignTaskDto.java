package com.ananops.provider.model.dto;

import com.ananops.base.dto.LoginAuthDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * Created By ChengHao On 2020/1/7
 */
@Data
@ApiModel
public class MdmcFeignTaskDto  {
//    private static final long serialVersionUID = -5107618122081378084L;

    @ApiModelProperty("任务")
    private MdmcAddTaskDto mdmcAddTaskDto;

    @ApiModelProperty("登录对象")
    private LoginAuthDto loginAuthDto;
}
