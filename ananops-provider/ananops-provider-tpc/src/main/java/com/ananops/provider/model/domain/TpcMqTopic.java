/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：TpcMqTopic.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.domain;

import com.ananops.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * The class Tpc mq topic.
 *
 * @author ananops.com @gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "an_tpc_mq_topic")
@Alias(value = "tpcMqTopic")
public class TpcMqTopic extends BaseEntity {
	private static final long serialVersionUID = 5642946024630652202L;

	/**
	 * 城市编码
	 */
	@Column(name = "topic_code")
	private String topicCode;

	/**
	 * 区域编码
	 */
	@Column(name = "topic_name")
	private String topicName;

	/**
	 * MQ类型, 10 rocketmq 20 kafka
	 */
	@Column(name = "mq_type")
	private Integer mqType;

	/**
	 * 消息类型, 10 无序消息, 20 无序消息
	 */
	@Column(name = "msg_type")
	private Integer msgType;

	/**
	 * 状态, 0生效,10,失效
	 */
	private Integer status;

	/**
	 * 备注
	 */
	private String remarks;
}