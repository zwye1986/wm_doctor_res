package com.pinde.sci.enums.edc;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum PatientTypeEnum implements GeneralEnum<String> {
	
	Real("Real","正式病例"),
	Test("Test","测试病例"),
	Disabled("Disabled","废止病例"),
	
	;

	private final String id;
	private final String name;
	
	PatientTypeEnum(String id,String name) {
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
		return EnumUtil.getById(id, PatientTypeEnum.class).getName();
	}
}
