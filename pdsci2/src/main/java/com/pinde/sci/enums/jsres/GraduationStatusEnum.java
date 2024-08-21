package com.pinde.sci.enums.jsres;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum GraduationStatusEnum implements GeneralEnum<String> {

	Agree("Agree","同意发证"),
	Suspension("Suspension","暂缓发证")
	;

	private final String id;
	private final String name;

	GraduationStatusEnum(String id, String name) {
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
		return EnumUtil.getById(id, GraduationStatusEnum.class).getName();
	}
}
