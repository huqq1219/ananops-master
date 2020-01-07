/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：AliyunSmsConfiguration.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.ananops.config.properties.AnanopsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * The class Aliyun sms configuration.
 *
 * @author ananops.com@gmail.com
 */
@Slf4j
@Configuration
public class AliyunSmsConfiguration {

	@Resource
	private AnanopsProperties ananOpsProperties;

	/**
	 * Acs client acs client.
	 *
	 * @return the acs client
	 *
	 * @throws ClientException the client exception
	 */
	@Bean
	public IAcsClient acsClient() throws ClientException {
		log.info("SMS Bean IAcsClient Start");
		IClientProfile profile = DefaultProfile.getProfile(ananOpsProperties.getAliyun().getSms().getRegionId(), ananOpsProperties.getAliyun().getKey().getAccessKeyId(), ananOpsProperties.getAliyun().getKey().getAccessKeySecret());
		DefaultProfile.addEndpoint(ananOpsProperties.getAliyun().getSms().getEndpointName(), ananOpsProperties.getAliyun().getSms().getRegionId(), ananOpsProperties.getAliyun().getSms().getProduct(), ananOpsProperties.getAliyun().getSms().getDomain());
		DefaultAcsClient defaultAcsClient = new DefaultAcsClient(profile);
		log.info("加载SMS Bean IAcsClient OK");
		return defaultAcsClient;
	}

}
