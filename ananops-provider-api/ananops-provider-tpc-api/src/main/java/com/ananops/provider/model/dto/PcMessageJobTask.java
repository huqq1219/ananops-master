/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：PcMessageJobTask.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto;

import lombok.Data;

import java.io.Serializable;


/**
 * The class Pc message job task.
 *
 * @author ananops.com @gmail.com
 */
@Data
public class PcMessageJobTask implements Serializable {

	private static final long serialVersionUID = -1689940882253489536L;

	/**
	 * 自增ID
	 */
	private String id;

	/**
	 * 版本号
	 */
	private String version;

	/**
	 * 消息key
	 */
	private Long messageKey;

	/**
	 * topic
	 */
	private String messageTopic;

	/**
	 * tag
	 */
	private String messageTag;
}