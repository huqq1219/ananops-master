package com.ananops.provider.model.dto.group;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户组织绑定用户Dto
 *
 * Created by bingyueduan on 2020/1/15.
 */
@Data
@ApiModel(value = "用户组织绑定用户Dto")
public class GroupBindUserDto implements Serializable {

    private static final long serialVersionUID = 3857784499077440327L;

    @ApiModelProperty(value = "组织ID")
    private Long groupId;

    @ApiModelProperty(value = "用户id")
    private List<Long> userIdList;
}
