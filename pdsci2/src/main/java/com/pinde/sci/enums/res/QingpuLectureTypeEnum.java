package com.pinde.sci.enums.res;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum QingpuLectureTypeEnum implements GeneralEnum<String> {

	TheoryTeaching("TheoryTeaching","理论授课"),
	Lecture("Lecture","小讲课"),
	AcademicLecture("AcademicLecture","学术讲座"),
	TrainingForTeachers("TrainingForTeachers","师资培训")
	;

	private final String id;
	private final String name;

	QingpuLectureTypeEnum(String id, String name) {
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
		return EnumUtil.getById(id, QingpuLectureTypeEnum.class).getName();
	}
}
