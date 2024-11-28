package com.pinde.core.common.enums;

import com.pinde.core.common.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum RegistryTypeEnum implements GeneralEnum<String> {//需与ResRecTypeEnum同步添加

    Lecture("Lecture", "学术讲座", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_N, com.pinde.core.common.GlobalConstant.FLAG_N),
    Project("Project", "教学项目", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_N, com.pinde.core.common.GlobalConstant.FLAG_N),
    DiseaseRegistryNew("DiseaseRegistryNew", "掌握病种", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_N, com.pinde.core.common.GlobalConstant.FLAG_N),
    SkillAndOperationRegistryNew("SkillAndOperationRegistryNew", "项目操作和手术", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_N, com.pinde.core.common.GlobalConstant.FLAG_N),
    TeachRecordRegistry("TeachRecordRegistry", "教学记录", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_N, com.pinde.core.common.GlobalConstant.FLAG_N),
    CaseRegistry("CaseRegistry", "大病历", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_N, com.pinde.core.common.GlobalConstant.FLAG_N),
    CaseRegistryHandwriting("CaseRegistryHandwriting", "手写大病历", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_N, com.pinde.core.common.GlobalConstant.FLAG_N),
    EmergencyCase("EmergencyCase", "门急诊病例", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_N, com.pinde.core.common.GlobalConstant.FLAG_N),
    DiseaseRegistry("DiseaseRegistry", "病种", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_Y),
    SkillRegistry("SkillRegistry", "操作技能", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_Y),
    OperationRegistry("OperationRegistry", "手术", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_Y),
    ManageBedRegistry("ManageBedRegistry", "管床记录", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_N),
    Grave("Grave", "危重记录", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_N, com.pinde.core.common.GlobalConstant.FLAG_N),
    AnnualTrainForm("AnnualTrainForm", "年度培训记录", com.pinde.core.common.GlobalConstant.FLAG_N, com.pinde.core.common.GlobalConstant.FLAG_N, com.pinde.core.common.GlobalConstant.FLAG_N),
    SkillAndOperationRegistry("SkillAndOperationRegistry", "操作技能和手术", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_Y),
    HospitalizationLog("HospitalizationLog", "住院志", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_N, com.pinde.core.common.GlobalConstant.FLAG_N),
    CampaignRegistry("CampaignRegistry", "参加活动", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_Y),
    CampaignNoItemRegistry("CampaignNoItemRegistry", "参与活动", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_N, com.pinde.core.common.GlobalConstant.FLAG_N),
    Internship("Internship", "实习记录", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_N, com.pinde.core.common.GlobalConstant.FLAG_N),
    CaseRecord("CaseRecord", "门诊病例", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_N, com.pinde.core.common.GlobalConstant.FLAG_N),

    JoinRescuePatients("JoinRescuePatients", "参加抢救病人情况记录", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_N, com.pinde.core.common.GlobalConstant.FLAG_N),
    TeachActivity("TeachActivity", "教学活动", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_N, com.pinde.core.common.GlobalConstant.FLAG_N),
    GrowthAndDevelopment("GrowthAndDevelopment", "小儿生长发育", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_N, com.pinde.core.common.GlobalConstant.FLAG_N),
    CommonDiseaseRegistry("CommonDiseaseRegistry", "常见病登记", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_N, com.pinde.core.common.GlobalConstant.FLAG_N),
    OtherCaseOfEyeSurgery("OtherCaseOfEyeSurgery", "眼科其他操作病例登记", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_N, com.pinde.core.common.GlobalConstant.FLAG_N),
    LanguageTeachingResearch("LanguageTeachingResearch", "外语、教学与科研要求", com.pinde.core.common.GlobalConstant.FLAG_Y, com.pinde.core.common.GlobalConstant.FLAG_N, com.pinde.core.common.GlobalConstant.FLAG_N),
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
    RegistryTypeEnum(String id, String name, String haveReq, String haveAppeal, String haveItem) {
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
