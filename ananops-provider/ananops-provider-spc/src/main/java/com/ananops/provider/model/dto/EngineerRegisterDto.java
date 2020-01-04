package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 工程师注册Dto
 *
 * Created by bingyueduan on 2020/1/2.
 */
@Data
@ApiModel(value = "工程师注册Dto")
public class EngineerRegisterDto implements Serializable {

    private static final long serialVersionUID = 2992724269040604069L;

    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名")
    private String loginName;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String userName;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String mobileNo;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 身份证号码
     */
    @ApiModelProperty(value = "身份证号码")
    private String identityNumber;

    /**
     * 工号
     */
    @ApiModelProperty(value = "工号")
    private String userCode;

    /**
     * 证书编号
     */
    @ApiModelProperty(value = "证书编号")
    private String titleCeNumber;
}
