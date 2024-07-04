package com.pinde.res.enums.xnres;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ResRecTypeEnum implements GeneralEnum<String> {
	CaseRegistry("CaseRegistry","大病历"),
	EmergencyCase("EmergencyCase","门急诊病例"),
	DiseaseRegistry("DiseaseRegistry","病种"),
	SkillAndOperationRegistry("SkillAndOperationRegistry","操作技能和手术"),
	HospitalizationLog("HospitalizationLog","住院志"),
	CampaignNoItemRegistry("CampaignNoItemRegistry","参加活动"),
	AfterSummary("AfterSummary","出科小结"),
	TeacherGrade("TeacherGrade","对带教老师评分"),
	DeptGrade("DeptGrade","对科室评分"),
	DOPS("DOPS","临床操作技能评估量化表"),
	Mini_CEX("Mini_CEX","迷你临床演练评估量化表"),
	AfterEvaluation("AfterEvaluation","出科考核"),
	MonthlyAssessment_clinic("MonthlyAssessment_clinic","月度考核表(门诊或医技)"),
	MonthlyAssessment_inpatientArea("MonthlyAssessment_inpatientArea","月度考核表(病区)"),
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
