package com.pinde.sci.enums.pubedu;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;


/**
 * Created by www.0001.Ga on 2017-05-03.
 */
public enum CourseDetailTypeEnum implements GeneralEnum<String> {
	CoursePPT("CoursePPT", "课程PPT"),
	CourseTest("CourseTest", "课程考试"),
	ChapterHandout("ChapterHandout", "课程讲义"),
	CourseData("CourseData", "课程资料"),
	DataPPT("DataPPT","资料PPT"),
	DataHandout("DataHandout","资料讲义"),
	ChapterVideo("ChapterVideo","资料视频")
	;
	private final String id;
	private final String name;

	CourseDetailTypeEnum(String id, String name) {
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
		return EnumUtil.getById(id, CourseDetailTypeEnum.class).getName();
	}
}
