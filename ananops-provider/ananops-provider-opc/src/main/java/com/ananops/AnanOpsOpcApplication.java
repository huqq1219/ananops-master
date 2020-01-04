/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：AnanOpsOpcApplication.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * The class AnanOps cloud opc application.
 *
 * @author ananops.com@gmail.com
 */
@EnableRetry
@EnableCaching
@EnableHystrix
@EnableSwagger2
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
public class AnanOpsOpcApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AnanOpsOpcApplication.class, args);
	}

	/**
	 * Get kaptcha bean default kaptcha.
	 *
	 * @return the default kaptcha
	 */
	@Bean(name = "captchaProducer")
	public DefaultKaptcha getKaptchaBean() {
		DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
		Properties properties = new Properties();
		properties.setProperty("kaptcha.border", "yes");
		properties.setProperty("kaptcha.border.color", "105,179,90");
		properties.setProperty("kaptcha.textproducer.font.color", "blue");
		properties.setProperty("kaptcha.image.width", "125");
		properties.setProperty("kaptcha.image.height", "45");
		properties.setProperty("kaptcha.session.key", "code");
		properties.setProperty("kaptcha.textproducer.char.length", "4");
		properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
		Config config = new Config(properties);
		defaultKaptcha.setConfig(config);
		return defaultKaptcha;
	}

	@Bean
	public SpringLiquibase springLiquibase(DataSource dataSource) {

		SpringLiquibase springLiquibase = new SpringLiquibase();

		springLiquibase.setDataSource(dataSource);
		springLiquibase.setChangeLog("classpath:/liquibase/index.xml");

		return springLiquibase;
	}

}
