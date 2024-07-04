package com.pinde.res.enums.ezhupei;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ResRecTypeEnum implements GeneralEnum<String> {
	CaseRegistry("CaseRegistry","大病历"),
	DiseaseRegistry("DiseaseRegistry","病种"),
	SkillRegistry("SkillRegistry","操作技能"),
	OperationRegistry("OperationRegistry","手术"),
	CampaignRegistry("CampaignRegistry","参加活动"),
	CampaignNoItemRegistry("CampaignNoItemRegistry","参加活动"),
	AfterSummary("AfterSummary","出科小结"),
	TeacherGrade("TeacherGrade","对带教老师评分"),
	DeptGrade("DeptGrade","对科室评分"),
	DOPS("DOPS","临床操作技能评估量化表"),
	AfterEvaluation("AfterEvaluation","出科考核表"),
	Mini_CEX("Mini_CEX","迷你临床演练评估量化表"),
	;

	private final String id;
	private final String name;


	ResRecTypeEnum(String id,String name) {
		this.id = id;
		this.name = name;
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
}
