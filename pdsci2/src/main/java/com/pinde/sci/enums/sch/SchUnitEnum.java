package com.pinde.sci.enums.sch;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum SchUnitEnum implements GeneralEnum<String> {
	Hour("Hour", "小时"),
	Day("Day", "日"),
	Week("Week", "周"),
	Month("Month", "月"),
	Year("Year", "年"),
	;

	private final String id;
	private final String name;
	
	SchUnitEnum(String id,String name) {
		this.id = id;
		this.name = name;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, SchUnitEnum.class).getName();
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
