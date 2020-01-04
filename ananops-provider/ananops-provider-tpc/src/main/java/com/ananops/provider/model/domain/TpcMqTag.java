/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：TpcMqTag.java
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
 * The class Tpc mq tag.
 *
 * @author ananops.com @gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "an_tpc_mq_tag")
@Alias(value = "tpcMqTag")
public class TpcMqTag extends BaseEntity {
	private static final long serialVersionUID = -1063353649973385058L;

	/**
	 * 主题ID
	 */
	@Column(name = "topic_id")
	private Long topicId;

	/**
	 * 城市编码
	 */
	@Column(name = "tag_code")
	private String tagCode;

	/**
	 * 区域编码
	 */
	@Column(name = "tag_name")
	private String tagName;

	/**
	 * 状态, 0生效,10,失效
	 */
	private Integer status;

	/**
	 * 备注
	 */
	private String remark;
}