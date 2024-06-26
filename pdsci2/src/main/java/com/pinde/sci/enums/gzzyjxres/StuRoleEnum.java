package com.pinde.sci.enums.gzzyjxres;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum StuRoleEnum implements GeneralEnum<String> {

	Doctor("Doctor","学员"),
	Teacher("Teacher","带教老师"),
	Secretary("Secretary","教学秘书")
	;

	private final String id;
	private final String name;

	StuRoleEnum(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, StuRoleEnum.class).getName();
	}
}
