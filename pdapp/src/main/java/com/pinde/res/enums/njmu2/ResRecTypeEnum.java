package com.pinde.res.enums.njmu2;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ResRecTypeEnum implements GeneralEnum<String> {
	TeachRecordRegistry("TeachRecordRegistry","教学记录","Y","教学记录"),
	CaseRegistry("CaseRegistry","大病历","Y","大病历"),
	SkillRegistry("SkillRegistry","操作技能","Y","操作技能"),
	ManageBedRegistry("ManageBedRegistry","管床记录","Y","管床记录"),
	Grave("Grave","危重记录","Y","危重记录"),
	CampaignRegistry("CampaignRegistry","参加活动","Y","参加活动"),
	Internship("Internship","实习记录","Y","实习记录"),
	TeacherGrade("TeacherGrade","对带教老师评分","N","带教老师评分"),
	DeptGrade("DeptGrade","对科室评分","N","科室评分"),
	AfterSummary("AfterSummary","出科小结","Y","出科小结"),
	CaseRecord("CaseRecord", "门诊病例", "Y", "门诊病例"),
	;

	private final String id;
	private final String name;
	private final String isForm;
	private final String typeName;


	ResRecTypeEnum(String id,String name,String isForm,String typeName) {
		this.id = id;
		this.name = name;
		this.isForm = isForm;
		this.typeName = typeName;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, ResRecTypeEnum.class).getName();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	public String getIsForm() {
		return isForm;
	}

	public String getTypeName() {
		return typeName;
	}
}
