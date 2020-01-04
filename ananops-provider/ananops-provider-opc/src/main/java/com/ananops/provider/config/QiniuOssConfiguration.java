/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：QiniuOssConfiguration.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.config;

import com.ananops.config.properties.AnanopsProperties;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * The class Qiniu oss configuration.
 *
 * @author ananops.com@gmail.com
 */
@Slf4j
@Configuration
public class QiniuOssConfiguration {

	@Resource
	private AnanopsProperties ananOpsProperties;

	/**
	 * Auth auth.
	 *
	 * @return the auth
	 */
	@Bean
	public Auth auth() {
		Auth auth = Auth.create(ananOpsProperties.getQiniu().getKey().getAccessKey(), ananOpsProperties.getQiniu().getKey().getSecretKey());
		log.info("Create Auth OK.");
		return auth;
	}

	/**
	 * Upload manager upload manager.
	 *
	 * @return the upload manager
	 */
	@Bean
	public UploadManager uploadManager() {
		Zone zone = Zone.autoZone();
		//创建上传对象
		UploadManager uploadManager = new UploadManager(new com.qiniu.storage.Configuration(zone));
		log.info("Create UploadManager OK.");
		return uploadManager;
	}

	/**
	 * Bucket manager bucket manager.
	 *
	 * @return the bucket manager
	 */
	@Bean
	public BucketManager bucketManager() {
		Zone zone = Zone.autoZone();
		//创建上传对象
		BucketManager uploadManager = new BucketManager(auth(), new com.qiniu.storage.Configuration(zone));
		log.info("Create BucketManager OK.");
		return uploadManager;
	}


}
