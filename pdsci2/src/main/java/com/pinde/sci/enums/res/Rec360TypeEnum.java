package com.pinde.sci.enums.res;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum Rec360TypeEnum implements GeneralEnum<String> {//需与ResRecTypeEnum同步添加

	Teacher_360("Teacher_360","带教评估住院医师"),
	Paramedic_360("Paramedic_360","临床护理人员对住院医师评价"),
	Patient_360("Patient_360","患者对住院医师评价"),
	TeacherGrade("TeacherGrade","学员对带教老师评分"),
	DeptGrade("DeptGrade","学员对科室评分")
	;

	private final String id;
	private final String name;

	Rec360TypeEnum(String id, String name) {
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
		return EnumUtil.getById(id, Rec360TypeEnum.class).getName();
	}
}
