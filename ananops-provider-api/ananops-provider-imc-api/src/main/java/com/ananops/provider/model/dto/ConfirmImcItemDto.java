package com.ananops.provider.model.dto;

import com.ananops.base.dto.LoginAuthDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * Created by rongshuai on 2020/1/7 16:27
 */
@Data
@ApiModel
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ConfirmImcItemDto implements Serializable {
    private static final long serialVersionUID = 1967146037784502141L;
    /**
     * 巡检任务子项ID
     */
    @ApiModelProperty(value = "巡检任务子项ID")
    private Long itemId;

    /**
     * 当前操作用户的身份信息
     */
    @ApiModelProperty(value = "当前操作用户的LoginAuthDto")
    private LoginAuthDto loginAuthDto;
}
