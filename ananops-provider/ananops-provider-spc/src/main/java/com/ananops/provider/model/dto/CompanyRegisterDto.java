package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 服务商注册DTO
 *
 * Created by bingyueduan on 2019/12/29.
 */
@Data
@ApiModel(value = "服务商注册Dto")
public class CompanyRegisterDto implements Serializable {

    private static final long serialVersionUID = -3468880006589264396L;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "登录名")
    private String groupName;

    /**
     * 统一社会信任代码
     */
    @ApiModelProperty(value = "统一社会信任代码")
    private String groupCode;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String loginPwd;

    /**
     * 确认密码
     */
    @ApiModelProperty(value = "确认密码")
    private String confirmPwd;

    /**
     * 联系人姓名
     */
    @ApiModelProperty(value = "联系人姓名")
    private String contact;

    /**
     * 联系人手机号
     */
    @ApiModelProperty(value = "联系人手机号")
    private String contactPhone;

    /**
     * 联系人邮箱
     */
    @ApiModelProperty(value = "联系人邮箱")
    private String email;

    /**
     * 手机短信验证码
     */
    @ApiModelProperty(value = "手机短信验证码")
    private String phoneSmsCode;

    /**
     * 注册token
     */
    @ApiModelProperty(value = "注册渠道")
    private String registerSource;
}
