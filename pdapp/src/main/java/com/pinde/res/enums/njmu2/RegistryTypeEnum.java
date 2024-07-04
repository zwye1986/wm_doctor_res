package com.pinde.res.enums.njmu2;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum RegistryTypeEnum implements GeneralEnum<String> {//需与ResRecTypeEnum同步添加
	
	TeachRecordRegistry("TeachRecordRegistry","教学记录","Y","N","N"),
	CaseRegistry("CaseRegistry","大病历","Y","N","N"),
	EmergencyCase("EmergencyCase","门急症病例","Y","N","N"),
	DiseaseRegistry("DiseaseRegistry","病种","Y","Y","Y"),
	SkillRegistry("SkillRegistry","操作技能","Y","Y","Y"),
	OperationRegistry("OperationRegistry","手术","Y","Y","Y"),
	ManageBedRegistry("ManageBedRegistry","管床记录","Y","Y","N"),
	Grave("Grave","危重记录","Y","N","N"),
	AnnualTrainForm("AnnualTrainForm","年度培训记录","N","N","N"),
	SkillAndOperationRegistry("SkillAndOperationRegistry","操作技能和手术","Y","Y","Y"),
	HospitalizationLog("HospitalizationLog","住院志","Y","Y","N"),
	CampaignRegistry("CampaignRegistry","参加活动","Y","Y","Y"),
	CampaignNoItemRegistry("CampaignNoItemRegistry","参与活动","Y","Y","N"),
	Internship("Internship","实习记录","Y","N","N"),
	CaseRecord("CaseRecord","门诊病例","Y","N","N"),
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
