package com.ananops.provider.model.vo.role;

import com.ananops.provider.model.domain.UacRole;
import com.ananops.provider.model.vo.RoleVo;
import lombok.Data;

import java.util.Set;

@Data
public class AndroidUacRoleVo {
    /**
     * 用户Id
     */
    private long userId;

    /**
     * 角色对象
     */
    private Set<UacRole> roles;

}
