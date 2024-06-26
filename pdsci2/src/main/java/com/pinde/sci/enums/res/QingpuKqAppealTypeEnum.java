package com.pinde.sci.enums.res;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum QingpuKqAppealTypeEnum implements GeneralEnum<String> {

	OfficialOut ("OfficialOut","公出"),
	Rest("Rest","休息"),
	SickLeave("Submit","病假"),
	AnnualLeave("AnnualLeave","年假")
	;

	private final String id;
	private final String name;

	QingpuKqAppealTypeEnum(String id, String name) {
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
		return EnumUtil.getById(id, QingpuKqAppealTypeEnum.class).getName();
	}
}
