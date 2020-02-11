package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 加盟服务商DTO
 *
 * Created by bingyueduan on 2019/12/28.
 */
@Data
@ApiModel
public class CompanyDto implements Serializable {

    private static final long serialVersionUID = 4115644363221151256L;

    @ApiModelProperty("加盟服务商ID")
    private Long id;

    @ApiModelProperty("UAC组织ID")
    private Long groupId;

    @ApiModelProperty("UAC组织名称")
    private String groupName;

    @ApiModelProperty("UAC用户ID")
    private Long userId;

    @ApiModelProperty("法人姓名")
    private String legalPersonName;

    @ApiModelProperty("法人联系方式")
    private String legalPersonPhone;

    @ApiModelProperty("法人身份证号")
    private String legalPersonNumber;
}
