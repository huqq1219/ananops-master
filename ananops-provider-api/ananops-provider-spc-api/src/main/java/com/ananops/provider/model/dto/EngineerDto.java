package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 工程师Dto
 *
 * Created by bingyueduan on 2019/12/30.
 */
@Data
@ApiModel
public class EngineerDto implements Serializable {

    private static final long serialVersionUID = -174414997527629871L;

    @ApiModelProperty("工程师ID")
    private Long id;

    @ApiModelProperty("UAC对应的用户ID")
    private Long userId;

    @ApiModelProperty("姓名")
    private String userName;

    @ApiModelProperty("手机号")
    private String mobileNo;

    @ApiModelProperty("账号状态. 0:在职, 1:离职")
    private String status;

    @ApiModelProperty("工号")
    private String userCode;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("所在省市")
    private String location;

    @ApiModelProperty("身份证号码")
    private String identityNumber;

    @ApiModelProperty("身份证有效期")
    private Date identityExpirationDate;

    @ApiModelProperty("职务")
    private String position;

    @ApiModelProperty("职称")
    private String title;

    @ApiModelProperty("学历")
    private String education;

    @ApiModelProperty("从业开始时间")
    private Date employmentStartTime;

    @ApiModelProperty("工程师身份证照片url")
    private String identityPhoto;

    @ApiModelProperty("职称证书编号")
    private String titleCeNumber;

    @ApiModelProperty("职称证书照片url")
    private String titleCePhoto;

    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("角色编码")
    private String roleCode;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色状态(启用/禁用)")
    private String roleStatus;
}
