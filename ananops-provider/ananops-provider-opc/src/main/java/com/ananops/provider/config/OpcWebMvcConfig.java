/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：OpcWebMvcConfig.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.config;

import com.ananops.core.config.PcObjectMapper;
import com.ananops.core.config.SwaggerConfiguration;
import com.ananops.core.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import java.util.List;


/**
 * The class Mdc web mvc config.
 *
 * @author ananops.com @gmail.com
 */
@Configuration
@EnableWebMvc
@Import(SwaggerConfiguration.class)
public class OpcWebMvcConfig extends WebMvcConfigurerAdapter {

	@Resource
	private TokenInterceptor vueViewInterceptor;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/");
	}

	/**
	 * Add interceptors.
	 *
	 * @param registry the registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(vueViewInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/swagger-resources/**", "*.js", "/**/*.js", "*.css", "/**/*.css", "*.html", "/**/*.html");
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		PcObjectMapper.buidMvcMessageConverter(converters);
	}

}
