package com.pinde.sci.enums.edu;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum EduStudyHistoryTypeEnum implements GeneralEnum<String> {
	
	Course("Course","学习课程"),
	Question("Question","问答提问"),
	Reply("Reply","问答回复"),
	Test("Test","测验")
	;

	private final String id;
	private final String name;
	
	EduStudyHistoryTypeEnum(String id,String name) {
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
		return EnumUtil.getById(id, EduStudyHistoryTypeEnum.class).getName();
	}
}
