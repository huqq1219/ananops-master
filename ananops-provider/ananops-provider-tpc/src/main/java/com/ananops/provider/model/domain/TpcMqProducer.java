/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：TpcMqProducer.java
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
 * The class Tpc mq producer.
 *
 * @author ananops.com @gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "an_tpc_mq_producer")
@Alias(value = "tpcMqProducer")
public class TpcMqProducer extends BaseEntity {
	private static final long serialVersionUID = -4064061704648362318L;

	/**
	 * 微服务名称
	 */
	@Column(name = "application_name")
	private String applicationName;

	/**
	 * PID 生产者组编码
	 */
	@Column(name = "producer_code")
	private String producerCode;

	/**
	 * PID 生产者组名称
	 */
	@Column(name = "producer_name")
	private String producerName;

	@Column(name = "query_message_url")
	private String queryMessageUrl;

	/**
	 * 状态, 0生效,10,失效
	 */
	private Integer status;

	/**
	 * 备注
	 */
	private String remark;
}