/*
 * Copyright (c) 2019. ananops.net All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：NotDisplaySql.java
 * 创建人：ananops
 * 联系方式：ananops.net@gmail.com


 *  * 平台官网: http://ananops.com
 */

package com.ananops.core.annotation;

import java.lang.annotation.*;

/**
 * 配合 SqlLogInterceptor 对指定方法 禁止打印SQL到控制台
 *
 * @author ananops.net @gmail.com
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface NotDisplaySql {
}
