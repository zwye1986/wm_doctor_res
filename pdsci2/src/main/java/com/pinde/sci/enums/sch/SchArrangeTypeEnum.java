package com.pinde.sci.enums.sch;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum SchArrangeTypeEnum implements GeneralEnum<String> {
	Auto("Auto", "智能"),
	SemiAuto("SemiAuto", "办自动"),
	Hand("Hand", "手动"),
	;

	private final String id;
	private final String name;
	
	SchArrangeTypeEnum(String id,String name) {
		this.id = id;
		this.name = name;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, SchArrangeTypeEnum.class).getName();
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
