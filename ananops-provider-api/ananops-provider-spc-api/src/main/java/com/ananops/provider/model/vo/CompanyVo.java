package com.ananops.provider.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 加盟服务商VO
 *
 * Created by bingyueduan on 2019/12/28.
 */
@Data
@ApiModel
public class CompanyVo implements Serializable {


    private static final long serialVersionUID = -6125444709558055693L;

    private Long id;

    private Long groupId;

    private String legalPersonName;

    private String legalPersonPhone;

    private String legalPersonNumber;
}
