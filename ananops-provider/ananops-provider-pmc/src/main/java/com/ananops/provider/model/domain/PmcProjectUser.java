package com.ananops.provider.model.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Table(name = "an_pmc_project_user")
@Data
@ApiModel
public class PmcProjectUser {
    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id")
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    @Column(name = "user_id")
    private Long userId;

}