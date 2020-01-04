/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：GaoDeUtil.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.utils;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ananops.provider.model.dto.gaode.GaodeLocation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * The class Gao de util.
 *
 * @author ananops.com @gmail.com
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GaoDeUtil {

	/**
	 * Gets city by ip addr.
	 *
	 * @param ipAddr the ip addr
	 *
	 * @return the city by ip addr
	 */
	public static GaodeLocation getCityByIpAddr(String ipAddr) {
		// http://lbs.amap.com/api/webservice/guide/api/ipconfig/
		log.info("getCityByIpAddr - 根据IP定位. ipAddr={}", ipAddr);
		GaodeLocation location = null;
		String urlAddressIp = "http://restapi.amap.com/v3/ip?key=f8bdce6f882a98635bb0b7b897331327&ip=%s";
		String url = String.format(urlAddressIp, ipAddr);
		try {
			String str = HttpClientUtil.get(HttpConfig.custom().url(url));
			location = new ObjectMapper().readValue(str, GaodeLocation.class);
		} catch (Exception e) {
			log.error("getCityByIpAddr={}", e.getMessage(), e);
		}
		log.info("getCityByIpAddr - 根据IP定位. ipAddr={}, location={}", ipAddr, location);
		return location;
	}
}
