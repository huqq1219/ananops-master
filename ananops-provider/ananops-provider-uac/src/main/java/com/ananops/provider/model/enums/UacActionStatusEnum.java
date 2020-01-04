/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：UacActionStatusEnum.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.enums;


/**
 * The enum Uac action status enum.
 *
 * @author ananops.com@gmail.com
 */
public enum UacActionStatusEnum {
	/**
	 * 启用
	 */
	ENABLE("ENABLE", "启用"),
	/**
	 * 禁用
	 */
	DISABLE("DISABLE", "禁用");

	/**
	 * The Type.
	 */
	String type;
	/**
	 * The Name.
	 */
	String name;

	UacActionStatusEnum(String type, String name) {
		this.type = type;
		this.name = name;
	}

	/**
	 * Gets type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets name.
	 *
	 * @param type the type
	 *
	 * @return the name
	 */
	public static String getName(String type) {
		for (UacActionStatusEnum ele : UacActionStatusEnum.values()) {
			if (type.equals(ele.getType())) {
				return ele.getName();
			}
		}
		return null;
	}

	/**
	 * Gets enum.
	 *
	 * @param type the type
	 *
	 * @return the enum
	 */
	public static UacActionStatusEnum getEnum(String type) {
		for (UacActionStatusEnum ele : UacActionStatusEnum.values()) {
			if (type.equals(ele.getType())) {
				return ele;
			}
		}
		return UacActionStatusEnum.ENABLE;
	}
}
