package com.pinde.sci.enums.jsres;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum JsResTeacherLevelEnum implements GeneralEnum<String> {

	GeneralFaculty("GeneralFaculty","一般师资"),
	KeyFaculty("KeyFaculty","骨干师资"),
	;

	private final String id;
	private final String name;



	JsResTeacherLevelEnum(String id, String name) {
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
		if (EnumUtil.getById(id, JsResTeacherLevelEnum.class) != null) {
			return EnumUtil.getById(id, JsResTeacherLevelEnum.class).getName();
		}else{
			return "";
		}

	}
}
