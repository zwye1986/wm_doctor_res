package com.pinde.res.enums.xnres;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ResRegistryTypeEnum implements GeneralEnum<String> {
	CaseRegistry("CaseRegistry","大病历"),
	EmergencyCase("EmergencyCase","门急诊病例"),
	DiseaseRegistry("DiseaseRegistry","病种"),
	SkillAndOperationRegistry("SkillAndOperationRegistry","操作技能和手术"),
	HospitalizationLog("HospitalizationLog","住院志"),
	CampaignNoItemRegistry("CampaignNoItemRegistry","参加活动"),
	;

	private final String id;
	private final String name;


	ResRegistryTypeEnum(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, ResRegistryTypeEnum.class).getName();
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
