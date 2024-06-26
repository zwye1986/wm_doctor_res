package com.pinde.sci.enums.pub;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum XjImpTypeEnum implements GeneralEnum<String> {
	EduStudentCourse("Edu_Student_Course","Edu_Student_Course"),
	;

	private final String id;
	private final String name;
	
	XjImpTypeEnum(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, XjImpTypeEnum.class).getName();
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
