package com.pinde.core.common.enums;

import com.pinde.core.util.EnumUtil;

public enum UserSexEnum implements GeneralEnum<String> {
	Man("Man", "男"),
	Woman("Woman", "女")
	;

	private final String id;
	private final String name;
	
	UserSexEnum(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, UserSexEnum.class).getName();
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
