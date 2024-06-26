package com.pinde.sci.enums.gyxjgl;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum XjglCourseTypeEnum implements GeneralEnum<String> {
	
	Degree("Degree","学位课程"),
	Optional("Optional","专业选修课"),
	OptionalNeed("OptionalNeed","专业必选课"),
	Public("Public","公共选修课"),
	PublicNeed("PublicNeed","公共必选课")
	;

	private final String id;
	private final String name;
	
	XjglCourseTypeEnum(String id,String name) {
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
		return EnumUtil.getById(id, XjglCourseTypeEnum.class).getName();
	}
}
