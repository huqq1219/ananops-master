/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：OpcOssFeignApi.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.service;

import com.ananops.provider.model.dto.oss.*;
import com.ananops.provider.service.hystrix.OpcOssFeignApiHystrix;
import com.ananops.security.feign.OAuth2FeignAutoConfiguration;
import com.ananops.wrapper.Wrapper;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * The interface Opc oss feign api.
 *
 * @author ananops.com @gmail.com
 */
@FeignClient(value = "ananops-provider-opc", configuration = OAuth2FeignAutoConfiguration.class, fallback = OpcOssFeignApiHystrix.class)
public interface OpcOssFeignApi {

	/**
	 * 上传文件.
	 *
	 * @param optUploadFileReqDto the opt upload file req dto
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/api/opc/oss/uploadFile")
	Wrapper<OptUploadFileRespDto> uploadFile(@RequestBody OptUploadFileReqDto optUploadFileReqDto);

	/**
	 * 获取附件完整路径.
	 *
	 * @param optGetUrlRequest the opt get url request
	 *
	 * @return the file url
	 */
	@PostMapping(value = "/api/opc/oss/getFileUrl")
	Wrapper<String> getFileUrl(@RequestBody OptGetUrlRequest optGetUrlRequest);

	/**
	 * List file url wrapper.
	 *
	 * @param urlRequest the url request
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/api/opc/oss/listFileUrl")
	Wrapper<List<ElementImgUrlDto>> listFileUrl(@RequestBody OptBatchGetUrlRequest urlRequest);

	/**
	 * Upload file with feign wrapper.
	 *
	 * @param file the file
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/api/opc/oss/handleFileUpload")
	Wrapper<OptUploadFileRespDto> handleFileUpload(@RequestPart(value = "file") MultipartFile file);

	/**
	 * 删除过期的文件.
	 */
	@PostMapping(value = "/api/opc/oss/deleteExpireFile")
	void deleteExpireFile();

	/**
	 * The class Multipart support config.
	 *
	 * @author ananops.com @gmail.com
	 */
	class MultipartSupportConfig {
		/**
		 * Feign form encoder encoder.
		 *
		 * @return the encoder
		 */
		@Bean
		public Encoder feignFormEncoder() {
			return new SpringFormEncoder();
		}
	}
}
