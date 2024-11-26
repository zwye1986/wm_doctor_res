package com.pinde.sci.enums.res;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum QingpuKqSubmitTypeEnum implements GeneralEnum<String> {

	Late("Late","迟到"),
	LeavePost("LeavePost","离岗"),
	Absence("Absence","缺勤")
	;

	private final String id;
	private final String name;

	QingpuKqSubmitTypeEnum(String id, String name) {
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
		return EnumUtil.getById(id, QingpuKqSubmitTypeEnum.class).getName();
	}
}
