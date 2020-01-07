package com.ananops.provider.model.dto.group;

import com.ananops.base.dto.BaseQuery;
import com.ananops.provider.model.domain.UacUser;
import com.ananops.provider.model.dto.role.BindUserDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@ApiModel(value = "GroupBindUacUserDto")
public class GroupBindUacUserDto extends BaseQuery implements Serializable {

    /**
     * 未绑定的用户集合
     */
    @ApiModelProperty(value = "所有用户集合")
    private Set<UacUser> allUserSet;

    /**
     * 已经绑定的用户集合
     */
    @ApiModelProperty(value = "已经绑定的用户集合")
    private Set<UacUser> alreadyBindUserIdSet;
}
