package com.pinde.sci.enums.pub;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum QuickDatePickEnum implements GeneralEnum<String> {

	Month("Month", "月"),
	Season("Season", "季"),
	Year("Year", "年"),
	;

	private final String id;
	private final String name;
	
	QuickDatePickEnum(String id,String name) {
		this.id = id;
		this.name = name;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, QuickDatePickEnum.class).getName();
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
