package com.pinde.core.common.enums;

import com.pinde.core.util.EnumUtil;

public enum ResRecTypeEnum implements GeneralEnum<String> {
	Lecture("Lecture","学术讲座登记表","Y","学术讲座"),
	Project("Project","教学项目登记表","Y","教学项目"),
	DiseaseRegistryNew("DiseaseRegistryNew","掌握病种","Y","掌握病种"),
	SkillAndOperationRegistryNew("SkillAndOperationRegistryNew","项目操作和手术登记表","Y","操作和手术"),
	CaseRegistry("CaseRegistry","大病历登记表","Y","大病历"),
	DiseaseRegistry("DiseaseRegistry","病种登记表","Y","病种"),
	OperationRegistry("OperationRegistry","手术登记表","Y","手术"),
	SkillRegistry("SkillRegistry","操作技能登记表","Y","操作技能"),
	SkillAndOperationRegistry("SkillAndOperationRegistry","操作技能和手术登记表","Y","操作技能和手术"),
	CampaignRegistry("CampaignRegistry","活动登记表","Y","参加活动"),
	CampaignNoItemRegistry("CampaignNoItemRegistry","参与活动","Y","参与活动"),
	TrainData("TrainData","培训数据","Y","培训数据"),
	AfterSummary("AfterSummary","出科小结","Y","出科小结"),
	AfterEvaluation("AfterEvaluation","出科考核表","Y","出科考核"),
	DiscipleSummary("DiscipleSummary","门诊跟师小结","Y","门诊跟师小结"),

	TeacherGrade("TeacherGrade","学员对带教老师评分","N","学员对带教老师评分"),
	DeptGrade("DeptGrade","学员对科室评分","N","学员对科室评分"),
	ManagerGrade("ManagerGrade","学员对继教科评分","N","学员对继教科评分"),
	HeadDoctorGrade("HeadDoctorGrade","科室对学员评分","N","科室对学员评分"),
	TeacherDoctorGrade("TeacherDoctorGrade","带教对学员评分","N","带教对学员评分"),
	PatientDoctorAssess360("PatientDoctorAssess360","病人对学员评分","N","病人对学员评分"),


	TeacherDoctorAssess("TeacherDoctorAssess","临床专业指导医师评估住院医师量表","N","临床专业指导医师评估住院医师量表"),
	TeacherDoctorAssessTwo("TeacherDoctorAssessTwo","医技专业指导医师评估住院医师量表","N","医技专业指导医师评估住院医师量表"),
	NurseDoctorAssess("NurseDoctorGrade","临床专业护士评估住院医师","N","临床专业护士评估住院医师"),
	TeacherAssess("TeacherAssess","住院医师评临床专业指导医师估量表","N","住院医师评临床专业指导医师估量表"),
	TeacherAssessTwo("TeacherAssessTwo","住院医师评估医技专业指导医师量表","N","住院医师评估医技专业指导医师量表"),


	TeachRegistry("TeachRegistry","教学登记","Y","教学登记"),
	ManageBedRegistry("ManageBedRegistry","管理病床登记表","Y","管床记录"),
	Grave("Grave","危重记录","Y","危重记录"),
	Internship("Internship","实习记录","Y","实习记录"),
	Ethics("Ethics","医德医风","Y","医德医风"),
	Document("Document","医学文案","Y","医学文案"),
	Appraisal("Appraisal","实习总鉴定","Y","实习总鉴定"),
	PreTrainForm("PreTrainForm","岗前培训表","Y","岗前培训表"),
	AnnualTrainForm("AnnualTrainForm","年度培训记录","Y","年度培训记录"),
	TeachRecordRegistry("TeachRecordRegistry","教学记录","Y","教学记录"),
	SpeAbilityAssess("SpeAbilityAssess","年度专业能力评估表","Y","年度专业能力评估表"),
	RegistryCheckForm("RegistryCheckForm","年度考核登记表","Y","年度考核登记表"),
	EmergencyCase("EmergencyCase","门急诊病例","Y","门急诊病例"),
	AnnualActivity("AnnualActivity","年度活动","Y","年度活动"),
	CourseScore("CourseScore","实习课程成绩单", "Y","实习课程成绩单"),
	HospitalizationLog("HospitalizationLog","住院志","Y","住院志"),
	ClinicalEnglish("ClinicalEnglish","临床英语应用","Y","临床英语应用"),
	NursingSkills("NursingSkills","护理操作技能","Y","护理操作技能"),
	DOPS("DOPS","临床操作技能评估（DOPS）量化表","Y","临床操作技能评估量化表"),
	Mini_CEX("Mini_CEX","迷你临床演练评估（Mini-CEX）量化表","Y","迷你临床演练评估量化表"),
	DoctorAuth("DoctorAuth","学员声明","N","学员声明"),
	Delay("Delay","延期","N","延期"),
	ReturnTraining("ReturnTraining","退培信息","N","退培信息"),
	CaseRecord("CaseRecord","门诊病例","Y","门诊病例"),
	MonthlyAssessment_clinic("MonthlyAssessment_clinic","月度考核表(门诊或医技)","Y","月度考核表(门诊或医技)"),
	MonthlyAssessment_inpatientArea("MonthlyAssessment_inpatientArea","月度考核表(病区)","Y","月度考核表(病区)"),
	PatientsAndDisease("PatientsAndDisease","病人病种","Y","病人病种"),
	HomeVisits("HomeVisits", "家庭随访","Y","家庭随访"),
	HypertensionMonitoring("HypertensionMonitoring","高血压监测","Y","高血压监测"),
	Type2DiabetesMonitoring("Type2DiabetesMonitoring","2型糖尿病监测","Y","2型糖尿病监测"),
	ChildHealthCare("ChildHealthCare","儿童保健","Y","儿童保健"),
	ElderHealthCare("ElderHealthCare","老年保健","Y","老年保健"),
	WomenHealthCare("WomenHealthCare","妇女保健","Y","妇女保健"),
	DisableHealthCare("DisableHealthCare","残疾人保健","Y","残疾人保健"),
	ImportantHealthCare("ImportantHealthCare","重点人群保健","Y","重点人群保健"),
	CommunityLearning("CommunityLearning","社区学习","Y","社区学习"),
	TheoreticalStudy("TheoreticalStudy","理论学习","Y","理论学习"),

	JoinRescuePatients("JoinRescuePatients","参加抢救病人情况记录","Y","参加抢救病人情况记录"),
	TeachActivity("TeachActivity","教学活动","Y","教学活动"),
	GrowthAndDevelopment("GrowthAndDevelopment","小儿生长发育","Y","小儿生长发育"),
	CommonDiseaseRegistry("CommonDiseaseRegistry","常见病登记","Y","常见病登记"),
	OtherCaseOfEyeSurgery("OtherCaseOfEyeSurgery","眼科其他操作病例登记","Y","眼科其他操作病例登记"),
	LanguageTeachingResearch("LanguageTeachingResearch","外语、教学与科研要求","Y","N"),
	SocialPractice("SocialPractice","社区实践","Y","社区实践"),
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
	public static String getNameById(String id) {
		return EnumUtil.getById(id, ResRecTypeEnum.class).getName();
	}
}
