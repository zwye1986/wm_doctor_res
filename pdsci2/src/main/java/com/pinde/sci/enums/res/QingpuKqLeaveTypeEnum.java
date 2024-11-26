package com.pinde.sci.enums.res;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum QingpuKqLeaveTypeEnum implements GeneralEnum<String> {

	SickLeave("SickLeave","病假"),
	Rest("Rest","休假"),
	Leave("Leave","事假"),
	MaternityLeave("MaternityLeave","产假")
	;

	private final String id;
	private final String name;

	QingpuKqLeaveTypeEnum(String id, String name) {
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
		return EnumUtil.getById(id, QingpuKqLeaveTypeEnum.class).getName();
	}
}
