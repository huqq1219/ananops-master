package com.ananops.provider.model.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Table(name = "an_imc_facilitator_manager_task")
@Alias(value = "imcFacilitatorManagerTask")
public class ImcFacilitatorManagerTask implements Serializable {
    private static final long serialVersionUID = -5051149424044758295L;
    /**
     * 巡检任务id
     */
    @Column(name = "task_id")
    private Long taskId;

    /**
     * 服务商管理员id
     */
    @Column(name = "facilitator_manager_id")
    private Long facilitatorManagerId;

}