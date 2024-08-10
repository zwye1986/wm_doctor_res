package com.pinde.sci.enums.edu;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ImpTypeEnum implements GeneralEnum<String> {
	EduStudentCourse("Edu_Student_Course","Edu_Student_Course"),
	;

	private final String id;
	private final String name;
	
	ImpTypeEnum(String id,String name) {
		this.id = id;
		this.name = name;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, ImpTypeEnum.class).getName();
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
