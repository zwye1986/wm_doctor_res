package com.pinde.sci.enums.exam;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum SubjectTypeEnum implements GeneralEnum<String> {
	
	Copy("Copy","复制"),
	Create("Create","创建")
	;

	private final String id;
	private final String name;
	
	SubjectTypeEnum(String id,String name) {
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
		return EnumUtil.getById(id, SubjectTypeEnum.class).getName();
	}
}
