package com.ananops.provider.model.dto;

import com.ananops.base.dto.LoginAuthDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * Created by rongshuai on 2020/1/4 14:01
 */
@Data
@ApiModel
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ItemChangeMaintainerDto implements Serializable {
    private static final long serialVersionUID = 3537760827278489942L;
    /**
     * 巡检任务子项ID
     */
    @ApiModelProperty(value = "巡检任务子项ID")
    private Long itemId;

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
