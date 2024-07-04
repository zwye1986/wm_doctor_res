package com.pinde.res.enums.jswjw;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum JsResDegreeCategoryEnum implements GeneralEnum<String>{
	Bachelor("Bachelor","学士学位"),
	AcademicMaster("AcademicMaster","学术型硕士"),
    AcademicDoctor("AcademicDoctor","学术型博士"),
    ClinicMaster("ClinicMaster","专业型硕士"),
    ClinicDoctor("ClinicDoctor","专业型博士"),
	NoDegree("NoDegree","暂无学位")
	;
	
	private final String id;
	private final String name;
	
	JsResDegreeCategoryEnum(String id, String name) {
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
		return EnumUtil.getById(id, JsResDegreeCategoryEnum.class).getName();
	}

}
