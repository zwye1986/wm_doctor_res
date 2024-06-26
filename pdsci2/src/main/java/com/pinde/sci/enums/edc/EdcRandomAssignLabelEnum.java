package com.pinde.sci.enums.edc;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum EdcRandomAssignLabelEnum implements GeneralEnum<String> {
	
	First("First","入组申请"),
	Follow("Follow","随访申请"),
	;

	private final String id;
	private final String name;
	
	EdcRandomAssignLabelEnum(String id,String name) {
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
		return EnumUtil.getById(id, EdcRandomAssignLabelEnum.class).getName();
	}
}
