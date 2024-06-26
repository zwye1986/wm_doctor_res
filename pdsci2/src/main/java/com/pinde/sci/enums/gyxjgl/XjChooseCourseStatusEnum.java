package com.pinde.sci.enums.gyxjgl;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum XjChooseCourseStatusEnum implements GeneralEnum<String>{

	Save("Save","已保存"),
	Choose("Choose","已选课"),
	UnChoose("UnChoose","未选课")
	;
	private final String id;
	private final String name;
	
	XjChooseCourseStatusEnum(String id, String name) {
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
		return EnumUtil.getById(id, XjChooseCourseStatusEnum.class).getName();
	}
}
