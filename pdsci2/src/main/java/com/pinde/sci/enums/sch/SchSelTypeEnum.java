package com.pinde.sci.enums.sch;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum SchSelTypeEnum implements GeneralEnum<String> {
	Fixed("Fixed", "固定选科"),
	Free("Free", "自由选科"),
	;

	private final String id;
	private final String name;
	
	SchSelTypeEnum(String id,String name) {
		this.id = id;
		this.name = name;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, SchSelTypeEnum.class).getName();
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
