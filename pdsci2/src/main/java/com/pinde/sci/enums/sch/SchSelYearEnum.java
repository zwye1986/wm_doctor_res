package com.pinde.sci.enums.sch;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum SchSelYearEnum implements GeneralEnum<String> {
	One("One", "一年"),
	Two("Two", "二年"),
	Three("Three", "三年"),
	;

	private final String id;
	private final String name;

	SchSelYearEnum(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, SchSelYearEnum.class).getName();
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
