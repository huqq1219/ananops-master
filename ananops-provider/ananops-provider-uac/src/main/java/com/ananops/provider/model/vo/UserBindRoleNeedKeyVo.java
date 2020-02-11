package com.ananops.provider.model.vo;

import com.ananops.provider.model.dto.user.BindRoleDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * Created By ChengHao On 2020/1/14
 */
@Data
@ApiModel(value = "角色绑定用户")
public class UserBindRoleNeedKeyVo implements Serializable {
    private static final long serialVersionUID = 7380000850031101695L;

    /**
     * 未绑定的用户集合
     */
    @ApiModelProperty(value = "所有用户集合")
    private Set<BindRoleDto> allRoleSet;

    /**
     * 已经绑定的用户集合
     */
    @ApiModelProperty(value = "已经绑定的用户集合")
    private Set<BindRoleDto> alreadyBindRoleSet;
}
