/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MallConstant.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.constant;

/**
 * The class Mall constant.
 *
 * @author ananops.com@gmail.com
 */
public class MallConstant {
	/**
	 * The enum Product status enum.
	 *
	 * @author ananops.com@gmail.com
	 */
	public enum ProductStatusEnum {
		/**
		 * On sale product status enum.
		 */
		ON_SALE(1, "在线");
		private String value;
		private int code;

		ProductStatusEnum(int code, String value) {
			this.code = code;
			this.value = value;
		}

		/**
		 * Gets value.
		 *
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/**
		 * Gets code.
		 *
		 * @return the code
		 */
		public int getCode() {
			return code;
		}
	}
}
