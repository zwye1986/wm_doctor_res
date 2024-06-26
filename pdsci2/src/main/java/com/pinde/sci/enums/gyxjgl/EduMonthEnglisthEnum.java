package com.pinde.sci.enums.gyxjgl;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum EduMonthEnglisthEnum implements GeneralEnum<String> {
	Jan("1","Jan."),
	Feb("2","Feb."),
	Mar("3","Mar."),
	Apr("4","Apr."),
	May("5","May"),
	Jun("6","Jun."),
	Jul("7","Jul."),
	Aug("8","Aug."),
	Sep("9","Sep."),
	Oct("10","Oct."),
	Nov("11","Nov."),
	Dec("12","Dec.");

	private final String id;
	private final String name;

	EduMonthEnglisthEnum(String id, String name) {
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
		return EnumUtil.getById(id, EduMonthEnglisthEnum.class).getName();
	}
}
