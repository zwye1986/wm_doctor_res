package com.pinde.core.common.enums;

import com.pinde.core.common.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ProfessionalTitleEnum implements GeneralEnum<String> {


	High("1","正高级职称"),
	SeniorHigh("2","副高级职称"),
	Middle("3","中级职称"),
	primary("4","初级职称");

	private final String id;
	private final String name;

	ProfessionalTitleEnum(String id, String name) {
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
		return EnumUtil.getById(id, ProfessionalTitleEnum.class).getName();
	}

	public static String getIdByName(String name) {
		for (ProfessionalTitleEnum value : ProfessionalTitleEnum.values()) {
			if (value.getName().equals(name)) {
				return value.getId();
			}
		}
		return "";
	}
}
