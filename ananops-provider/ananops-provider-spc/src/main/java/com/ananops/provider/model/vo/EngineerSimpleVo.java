package com.ananops.provider.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 工程师名字及idVo
 *
 * Created by bingyueduan on 2020/1/15.
 */
@EqualsAndHashCode
@Data
public class EngineerSimpleVo implements Serializable {

    private static final long serialVersionUID = -4562199610231071582L;

    /**
     * 工程师id
     */
    private Long id;

    /**
     * 工程师名字
     */
    private String name;
}
