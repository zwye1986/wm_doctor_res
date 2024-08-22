package com.pinde.sci.enums.pub;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum LogTypeEnum implements GeneralEnum<String> {
	Day("Day", "日记"),
	Week("Week", "周记"),
	Month("Month", "月记")
	;

	private final String id;
	private final String name;
	
	LogTypeEnum(String id,String name) {
		this.id = id;
		this.name = name;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, LogTypeEnum.class).getName();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
}
