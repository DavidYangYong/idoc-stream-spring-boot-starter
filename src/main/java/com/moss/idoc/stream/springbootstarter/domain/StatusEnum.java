package com.moss.idoc.stream.springbootstarter.domain;

/**
 * @author david
 * @create 2019-10-03 10:06
 **/
public enum StatusEnum {
	ORIGINAL("009", "Original"), DELETE("003", "Delete"), CHANGE("004", "Change"), REPLACE("005", "Replace"), RESEND(
			"018", "Resend"), DELETEFLAG("X", "DeleteFlag");
	private final String key;
	private final String value;

	private StatusEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * 根据key获取枚举
	 */

	public static StatusEnum getEnumByKey(String key) {
		if (null == key) {
			return null;
		}
		for (StatusEnum temp : StatusEnum.values()) {
			if (temp.getKey().equals(key)) {
				return temp;
			}
		}
		return null;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
}
