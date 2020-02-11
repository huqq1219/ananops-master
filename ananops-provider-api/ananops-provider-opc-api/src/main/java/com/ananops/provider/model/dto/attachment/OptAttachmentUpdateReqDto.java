package com.ananops.provider.model.dto.attachment;

import com.ananops.base.dto.LoginAuthDto;
import lombok.Data;

import java.io.Serializable;

/**
 * 增加绑定关系
 *
 * @author Bingyue Duan
 *
 * @version 1.0
 *
 * @date 2020-02-11 19:56
 */
@Data
public class OptAttachmentUpdateReqDto implements Serializable {

    private static final long serialVersionUID = 8939629369193446805L;

    /**
     * 附件Id
     */
    private Long id;

    /**
     * 上传附件的相关业务流水号
     */
    private String refNo;

    /**
     * 上传附件描述
     */
    private String description;

    /**
     * 修改人信息
     */
    private LoginAuthDto loginAuthDto;
}
