/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：RobotMsgTypeEnum.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.model.enums;

/**
 * The enum Robot msg type enum.
 *
 * @author ananops.com @gmail.com
 */
public enum RobotMsgTypeEnum {
	/**
	 * 操作日志
	 */
	MARKDOWN("markdown", "markdown"),
	/**
	 * Link robot msg type enum.
	 */
	LINK("link", "link"),
	/**
	 * Text robot msg type enum.
	 */
	TEXT("text", "text"),;

	/**
	 * The Type.
	 */
	String type;
	/**
	 * The Name.
	 */
	String name;

	RobotMsgTypeEnum(String type, String name) {
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
		for (RobotMsgTypeEnum ele : RobotMsgTypeEnum.values()) {
			if (ele.getType().equals(type)) {
				return ele.getName();
			}
		}
		return null;
	}

}
