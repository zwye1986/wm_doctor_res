package com.pinde.sci.enums.fstu;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum AcademicAndScoreEnum implements GeneralEnum<String> {

	//学术申请审核状态
	AcaAtyAdd("Add","未提交"),
	AcaAtyPassing("Passing","未审核"),
	AcaAtyPassed("Passed","已审核"),
	AcaAtyBacked("Backed","退回"),
	//学术报销申请审核状态
	AcaExpAdd("Add","报销未提交"),
	AcaExpPassing("Passing","报销审核中"),
	AcaExpPassed("Passed","报销已审核"),
	AcaExpBacked("Backed","报销被驳回"),
	//学分管理审核状态
	ScoreAdd("Add","未提交"),
	ScorePassing("Passing","未审核"),
	ScorePassed("Passed","已审核"),
	ScoreBacked("Backed","退回"),
	;

	private final String id;
	private final String name;

	AcademicAndScoreEnum(String id, String name) {
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
		return EnumUtil.getById(id, AcademicAndScoreEnum.class).getName();
	}
}
