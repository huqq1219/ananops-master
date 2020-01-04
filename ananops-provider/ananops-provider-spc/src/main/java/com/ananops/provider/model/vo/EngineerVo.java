package com.ananops.provider.model.vo;

import com.ananops.base.dto.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 加盟服务商查询返回的工程师信息Vo
 *
 * Created by bingyueduan on 2020/1/2.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EngineerVo extends BaseVo {

    /**
     * UAC对应的用户ID
     */
    private Long userId;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 工号
     */
    private String userCode;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 手机号
     */
    private String mobileNo;

    /**
     * 状态
     */
    private String status;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 描述
     */
    private String remark;

    /**
     * 性别
     */
    private String sex;

    /**
     * 所在省市
     */
    private String location;

    /**
     * 身份证号码
     */
    private String identityNumber;

    /**
     * 身份证有效期
     */
    private Date identityExpirationDate;

    /**
     * 职务
     */
    private String position;

    /**
     * 职称
     */
    private String title;

    /**
     * 学历
     */
    private String education;

    /**
     * 从业开始时间
     */
    private Date employmentStartTime;

    /**
     * 工程师身份证照片url
     */
    private String identityPhoto;

    /**
     * 职称证书编号
     */
    private String titleCeNumber;

    /**
     * 职称证书发放日期
     */
    private Date titleCeTime;

    /**
     * 发放职称证书单位
     */
    private String titleCeAuthority;

    /**
     * 职称证书照片url
     */
    private String titleCePhoto;

    /**
     * 用户所属的组织ID
     */
    private Long groupId;

    /**
     * 用户所属的组织名称
     */
    private String groupName;
}
