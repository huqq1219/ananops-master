package com.ananops.provider.model.dto;

import com.ananops.base.dto.LoginAuthDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * Created by rongshuai on 2020/1/8 18:58
 */
@Data
@ApiModel
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MdmcChangeMaintainerDto implements Serializable {
    private static final long serialVersionUID = -1351895744101136518L;

    /**
     * 维修维护任务ID
     */
    @ApiModelProperty(value = "维修维护任务ID")
    private Long taskId;

    /**
     * 修改后的工程师ID
     */
    @ApiModelProperty(value = "修改后的工程师ID")
    private Long maintainerId;

    /**
     * 当前操作用户的身份信息
     */
    @ApiModelProperty(value = "当前操作用户的LoginAuthDto")
    private LoginAuthDto loginAuthDto;
}
