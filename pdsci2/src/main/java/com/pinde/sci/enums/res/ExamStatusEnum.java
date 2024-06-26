package com.pinde.sci.enums.res;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ExamStatusEnum implements GeneralEnum<String> {
	
	Arrange("Arrange","待安排"),
	Finished("Finished","已安排"),
	;
	private final String id;
	private final String name;
	


	ExamStatusEnum(String id,String name) {
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
		return EnumUtil.getById(id, ExamStatusEnum.class).getName();
	}
}
