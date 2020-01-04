/*
 * Copyright (c) 2019. ananops.net All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：ZookeeperInitRunner.java
 * 创建人：ananops
 * 联系方式：ananops.net@gmail.com


 *  * 平台官网: http://ananops.com
 */

package com.ananops.core.config;

import com.ananops.config.properties.AnanopsProperties;
import com.ananops.core.registry.RegistryCenterFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetAddress;

/**
 * The class Redis init runner.
 *
 * @author ananops.net @gmail.com
 */
@Component
@Order
@Slf4j
public class ZookeeperInitRunner implements CommandLineRunner {
	@Resource
	private AnanopsProperties ananOpsProperties;
	@Value("${spring.application.name}")
	private String applicationName;

	/**
	 * Run.
	 *
	 * @param args the args
	 *
	 * @throws Exception the exception
	 */
	@Override
	public void run(String... args) throws Exception {
		String hostAddress = InetAddress.getLocalHost().getHostAddress();
		log.info("###ZookeeperInitRunner，init. HostAddress={}, applicationName={}", hostAddress, applicationName);
		RegistryCenterFactory.startup(ananOpsProperties, hostAddress, applicationName);//进入注册中心
		log.info("###ZookeeperInitRunner，finish<<<<<<<<<<<<<");
	}

}