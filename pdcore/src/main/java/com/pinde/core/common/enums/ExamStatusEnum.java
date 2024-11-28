package com.pinde.core.common.enums;

import com.pinde.core.common.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ExamStatusEnum implements GeneralEnum<String> {
	
	Arrange("Arrange","待安排"),
	Finished("Finished","已安排"),
    Waiting("Waiting", "排队"),
    StayAssessment("StayAssessment", "待考核"),
    Assessment("Assessment", "已考核"),
    AssessIng("AssessIng", "考核中");
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
        return EnumUtil.getById(id, com.pinde.core.common.enums.ExamStatusEnum.class).getName();
	}
}
