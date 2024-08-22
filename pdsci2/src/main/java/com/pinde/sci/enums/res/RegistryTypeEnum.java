package com.pinde.sci.enums.res;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum RegistryTypeEnum implements GeneralEnum<String> {//需与ResRecTypeEnum同步添加

	Lecture("Lecture","学术讲座","Y","N","N"),
	Project("Project","教学项目","Y","N","N"),
	DiseaseRegistryNew("DiseaseRegistryNew","掌握病种","Y","N","N"),
	SkillAndOperationRegistryNew("SkillAndOperationRegistryNew","项目操作和手术","Y","N","N"),
	TeachRecordRegistry("TeachRecordRegistry","教学记录","Y","N","N"),
	CaseRegistry("CaseRegistry","大病历","Y","N","N"),
	CaseRegistryHandwriting("CaseRegistryHandwriting","手写大病历","Y","N","N"),
	EmergencyCase("EmergencyCase","门急诊病例","Y","N","N"),
	DiseaseRegistry("DiseaseRegistry","病种","Y","Y","Y"),
	SkillRegistry("SkillRegistry","操作技能","Y","Y","Y"),
	OperationRegistry("OperationRegistry","手术","Y","Y","Y"),
	ManageBedRegistry("ManageBedRegistry","管床记录","Y","Y","N"),
	Grave("Grave","危重记录","Y","N","N"),
	AnnualTrainForm("AnnualTrainForm","年度培训记录","N","N","N"),
	SkillAndOperationRegistry("SkillAndOperationRegistry","操作技能和手术","Y","Y","Y"),
	HospitalizationLog("HospitalizationLog","住院志","Y","N","N"),
	CampaignRegistry("CampaignRegistry","参加活动","Y","Y","Y"),
	CampaignNoItemRegistry("CampaignNoItemRegistry","参与活动","Y","N","N"),
	Internship("Internship","实习记录","Y","N","N"),
	CaseRecord("CaseRecord","门诊病例","Y","N","N"),

    JoinRescuePatients("JoinRescuePatients","参加抢救病人情况记录","Y","N","N"),
    TeachActivity("TeachActivity","教学活动","Y","N","N"),
    GrowthAndDevelopment("GrowthAndDevelopment","小儿生长发育","Y","N","N"),
    CommonDiseaseRegistry("CommonDiseaseRegistry","常见病登记","Y","N","N"),
    OtherCaseOfEyeSurgery("OtherCaseOfEyeSurgery","眼科其他操作病例登记","Y","N","N"),
	LanguageTeachingResearch("LanguageTeachingResearch","外语、教学与科研要求","Y","N","N"),
	;

	private final String id;
	private final String name;
	private final String haveReq;
	private final String haveAppeal;
	private final String haveItem;

	/**
	 *
	 * @param id
	 * @param name
	 * @param haveReq 要求
	 * @param haveAppeal 申述
     * @param haveItem 是否有子项
     */
	RegistryTypeEnum(String id,String name,String haveReq,String haveAppeal,String haveItem) {
		this.id = id;
		this.name = name;
		this.haveReq = haveReq;
		this.haveAppeal = haveAppeal;
		this.haveItem = haveItem;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public String getHaveAppeal(){
		return haveAppeal;
	}
	
	public String getHaveReq(){
		return haveReq;
	}
	
	public String getHaveItem(){
		return haveItem;
	}
	
	public static String getNameById(String id) {
		return EnumUtil.getById(id, RegistryTypeEnum.class).getName();
	}
}
