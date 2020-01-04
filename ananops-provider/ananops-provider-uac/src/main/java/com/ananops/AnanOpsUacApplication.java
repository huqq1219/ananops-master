/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：AnanOpsUacApplication.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops;

import com.ananops.provider.service.impl.PcSmsCodeSender;
import com.ananops.security.core.code.sms.SmsCodeSender;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * The class AnanOps cloud uac application.
 *
 * @author ananops.com@gmail.com
 */
@EnableHystrix
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
public class AnanOpsUacApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AnanOpsUacApplication.class, args);
	}

	@Bean
	public SpringLiquibase springLiquibase(DataSource dataSource) {

		SpringLiquibase springLiquibase = new SpringLiquibase();

		springLiquibase.setDataSource(dataSource);
		springLiquibase.setChangeLog("classpath:/liquibase/index.xml");

		return springLiquibase;
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:org/springframework/security/messages_zh_CN");
		return messageSource;
	}

	@Bean
	public SmsCodeSender smsCodeSender() {
		return new PcSmsCodeSender();
	}
}
