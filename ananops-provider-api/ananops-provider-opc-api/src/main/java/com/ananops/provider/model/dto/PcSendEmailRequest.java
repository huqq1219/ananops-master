/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：PcSendEmailRequest.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * The class Pc send email request.
 *
 * @author ananops.com@gmail.com
 */
@Data
public class PcSendEmailRequest implements Serializable {

	private static final long serialVersionUID = 8391763073747981998L;
	private String text;
	private String subject;
	private Set<String> to;
}
