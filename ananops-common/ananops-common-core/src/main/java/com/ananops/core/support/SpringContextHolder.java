/*
 * Copyright (c) 2019. ananops.net All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：SpringContextHolder.java
 * 创建人：ananops
 * 联系方式：ananops.net@gmail.com


 *  * 平台官网: http://ananops.com
 */

package com.ananops.core.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * The class Spring context holder.
 *
 * @author ananops.net @gmail.com
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	/**
	 * Sets application context.
	 *
	 * @param applicationContext the application context
	 *
	 * @throws BeansException the beans exception
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringContextHolder.applicationContext = applicationContext;
	}

	/**
	 * Gets application context.
	 *
	 * @return the application context
	 */
	public static ApplicationContext getApplicationContext() {
		assertApplicationContext();
		return applicationContext;
	}

	/**
	 * Gets bean.
	 *
	 * @param <T>      the type parameter
	 * @param beanName the bean name
	 *
	 * @return the bean
	 */
	public static <T> T getBean(String beanName) {
		assertApplicationContext();
		return (T) applicationContext.getBean(beanName);
	}

	/**
	 * Gets bean.
	 *
	 * @param <T>          the type parameter
	 * @param requiredType the required type
	 *
	 * @return the bean
	 */
	public static <T> T getBean(Class<T> requiredType) {
		assertApplicationContext();
		return applicationContext.getBean(requiredType);
	}

	public static DefaultListableBeanFactory getDefaultListableBeanFactory() {
		assertApplicationContext();
		return (DefaultListableBeanFactory) ((ConfigurableApplicationContext) applicationContext).getBeanFactory();
	}

	private static void assertApplicationContext() {
		if (SpringContextHolder.applicationContext == null) {
			throw new IllegalArgumentException("applicationContext属性为null,请检查是否注入了SpringContextHolder!");
		}
	}

}
