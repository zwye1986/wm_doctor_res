package com.pinde.sci.enums.sch;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum SchCycleTypeEnum implements GeneralEnum<String> {
	OneYear("OneYear", "每年"),
	AllYear("AllYear", "全年"),
	;

	private final String id;
	private final String name;

	SchCycleTypeEnum(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, SchCycleTypeEnum.class).getName();
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
