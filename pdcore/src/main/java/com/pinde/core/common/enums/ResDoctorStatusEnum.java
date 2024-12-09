package com.pinde.core.common.enums;

import com.pinde.core.common.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ResDoctorStatusEnum implements GeneralEnum<String> {
	Training("Training","在培"),
	Terminat("Terminat","终止"),
	Graduation("Graduation","结业"),
	;

	private final String id;
	private final String name;
	
	ResDoctorStatusEnum(String id,String name) {
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
		return EnumUtil.getById(id, ResDoctorStatusEnum.class).getName();
	}
}
