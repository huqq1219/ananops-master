/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：Md5Util.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * The class Md 5 util.
 *
 * @author ananops.com @gmail.com
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Md5Util {

	/**
	 * Encrypt string.
	 *
	 * @param password 密码
	 *
	 * @return the string
	 */
	public static String encrypt(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

	/**
	 * 密码是否匹配.
	 *
	 * @param rawPassword     明文
	 * @param encodedPassword 密文
	 *
	 * @return the boolean
	 */
	public static boolean matches(CharSequence rawPassword, String encodedPassword) {
		return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
	}

}
