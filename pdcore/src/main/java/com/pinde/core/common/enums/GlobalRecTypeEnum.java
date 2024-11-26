package com.pinde.core.common.enums;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.util.EnumUtil;

public enum GlobalRecTypeEnum implements GeneralEnum<String> {//需与ResRecTypeEnum同步添加

    Ethics("Ethics", "医德医风", GlobalConstant.RES_ROLE_SCOPE_ADMIN),
	Document("Document","医学文案",GlobalConstant.RES_ROLE_SCOPE_ADMIN),
	NursingSkills("NursingSkills","护理操作技能",GlobalConstant.RES_ROLE_SCOPE_ADMIN),
	ClinicalEnglish("ClinicalEnglish","临床英语应用",GlobalConstant.RES_ROLE_SCOPE_ADMIN),
	Appraisal("Appraisal","实习总鉴定",GlobalConstant.RES_ROLE_SCOPE_ADMIN),
	SpeAbilityAssess("SpeAbilityAssess","年度专业能力评估表",GlobalConstant.RES_ROLE_SCOPE_MANAGER+","+GlobalConstant.RES_ROLE_SCOPE_ADMIN),
	RegistryCheckForm("RegistryCheckForm","年度考核登记表",GlobalConstant.RES_ROLE_SCOPE_HEAD+","+GlobalConstant.RES_ROLE_SCOPE_ADMIN),
	AnnualActivity("AnnualActivity","年度活动",GlobalConstant.RES_ROLE_SCOPE_MANAGER+","+GlobalConstant.RES_ROLE_SCOPE_ADMIN),
	CourseScore("CourseScore","实习课程成绩单",GlobalConstant.RES_ROLE_SCOPE_MANAGER+","+GlobalConstant.RES_ROLE_SCOPE_ADMIN),
	;

	private final String id;
	private final String name;
	private final String auditScope;

	GlobalRecTypeEnum(String id,String name,String auditScope) {
		this.id = id;
		this.name = name;
		this.auditScope = auditScope;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public String getAuditScope() {
		return auditScope;
	}
	
	public static String getNameById(String id) {
		return EnumUtil.getById(id, GlobalRecTypeEnum.class).getName();
	}
}
