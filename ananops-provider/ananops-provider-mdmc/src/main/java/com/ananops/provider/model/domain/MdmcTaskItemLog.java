package com.ananops.provider.model.domain;


import com.ananops.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "task_item_log")
public class MdmcTaskItemLog extends BaseEntity {
    private static final long serialVersionUID = -675895374365921103L;
    /**
     * 对应的任务ID
     */
    @Column(name = "task_id")
    private Long taskId;

    /**
     * 当前操作对应的状态
     */
    private Integer status;

    /**
     * 当前发生动作的描述（维修工或甲方用户填写）
     */
    private String movement;

    /**
     * 对应的任务子项ID
     */
    @Column(name = "task_item_id")
    private Long taskItemId;

    /**
     * 当前发生操作对应的时间戳
     */
    @Column(name = "status_timestamp")
    private Date statusTimestamp;

}