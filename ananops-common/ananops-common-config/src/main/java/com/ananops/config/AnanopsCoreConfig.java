/*
 * Copyright (c) 2019. ananops.net All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：AnanOpsCoreConfig.java
 * 创建人：ananops
 * 联系方式：ananops.net@gmail.com


 *  * 平台官网: http://ananops.com
 */
package com.ananops.config;


import com.ananops.config.properties.AnanopsProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The class Aliyun core config.
 *
 * @author ananops.net @gmail.com
 */
@Configuration
@EnableConfigurationProperties(AnanopsProperties.class)
public class AnanopsCoreConfig {
}
