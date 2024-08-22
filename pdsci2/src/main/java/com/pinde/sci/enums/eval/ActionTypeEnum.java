package com.pinde.sci.enums.eval;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ActionTypeEnum implements GeneralEnum<String> {

	SINGLE("SINGLE", "单人"),
	MANY("MANY", "多人")
	;

	private final String id;
	private final String name;

	ActionTypeEnum(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, ActionTypeEnum.class).getName();
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
