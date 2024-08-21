package com.pinde.sci.enums.pub;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum PatientSourceEnum implements GeneralEnum<String> {

	OutPatient("OutPatient", "门诊"),
	InPatient("InPatient", "住院"),
	;

	private final String id;
	private final String name;
	
	PatientSourceEnum(String id,String name) {
		this.id = id;
		this.name = name;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, PatientSourceEnum.class).getName();
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
