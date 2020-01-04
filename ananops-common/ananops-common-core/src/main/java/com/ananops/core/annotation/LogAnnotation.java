/*
 * Copyright (c) 2019. ananops.net All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：LogAnnotation.java
 * 创建人：ananops
 * 联系方式：ananops.net@gmail.com


 *  * 平台官网: http://ananops.com
 */

package com.ananops.core.annotation;

import com.ananops.core.enums.LogTypeEnum;

import java.lang.annotation.*;


/**
 * 操作日志.
 *
 * @author ananops.net@gmail.com
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
	/**
	 * 日志类型
	 *
	 * @return the log type enum
	 */
	LogTypeEnum logType() default LogTypeEnum.OPERATION_LOG;

	/**
	 * 是否保存请求的参数
	 *
	 * @return the boolean
	 */
	boolean isSaveRequestData() default false;

	/**
	 * 是否保存响应的结果
	 *
	 * @return the boolean
	 */
	boolean isSaveResponseData() default false;
}
