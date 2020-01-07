package com.ananops.provider.model.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "an_imc_facilitator_group_task")
@Alias(value = "imcFacilitatorGroupTask")
public class ImcFacilitatorGroupTask implements Serializable {
    private static final long serialVersionUID = 5317237692596579134L;
    /**
     * 服务商组织id
     */
    @Column(name = "facilitator_group_id")
    private Long facilitatorGroupId;

    /**
     * 巡检任务id
     */
    @Column(name = "task_id")
    private Long taskId;

}