package com.pinde.sci.enums.jszy;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum JszyResDegreeCategoryEnum implements GeneralEnum<String>{
	Bachelor("Bachelor","学士学位"),
	AcademicMaster("AcademicMaster","学术型硕士"),
	AcademicDoctor("AcademicDoctor","学术型博士"),
	ClinicMaster("ClinicMaster","临床型硕士"),
	ClinicDoctor("ClinicDoctor","临床型博士"),
	NoDegree("NoDegree","暂无学位")
	;
	
	private final String id;
	private final String name;
	
	JszyResDegreeCategoryEnum(String id, String name) {
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
		return EnumUtil.getById(id, JszyResDegreeCategoryEnum.class).getName();
	}

}
