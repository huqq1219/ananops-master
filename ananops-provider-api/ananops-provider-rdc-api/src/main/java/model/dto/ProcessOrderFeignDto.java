package model.dto;

import com.ananops.base.dto.LoginAuthDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel
public class ProcessOrderFeignDto implements Serializable {
    private static final long serialVersionUID = -6655429519272958337L;

    @ApiModelProperty("备品备件订单编号")
    @JsonProperty("deviceOrderId")
    private Long id;

    @ApiModelProperty("备品备件订单当前处理状态(1审核中，2审核完成)")
    private Integer status;

    @ApiModelProperty("备品备件订单总金额")
    private BigDecimal totalPrice;

    @ApiModelProperty("优惠折扣")
    private Float discount;

    @ApiModelProperty("备品备件订单绑定的对象的类型（1.维修工单 2.企业 3. 个人）")
    private Integer objectType;

    @ApiModelProperty("备品备件订单绑定的对象的编号")
    private Long objectId;

    @ApiModelProperty("备品备件订单一级审核人编号")
    private Long nextApproverId;

    @ApiModelProperty("备品备件订单一级审核人")
    private String nextApprover;

    @ApiModelProperty("备品备件订单审核结果")
    private String result;

    @ApiModelProperty("备品备件订单审核意见")
    private String suggestion;

    private LoginAuthDto loginAuthDto;
}
