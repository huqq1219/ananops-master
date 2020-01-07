/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：AnanOpsZipkinApplication.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.EnableZipkinServer;

/**
 * The class AnanOps cloud zipkin application.
 *
 * @author ananops.com@gmail.com
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableZipkinServer
public class AnanOpsZipkinApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AnanOpsZipkinApplication.class, args);
	}
}
