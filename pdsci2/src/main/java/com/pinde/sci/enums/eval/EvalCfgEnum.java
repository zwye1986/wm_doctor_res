package com.pinde.sci.enums.eval;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum EvalCfgEnum implements GeneralEnum<String> {

	ALL("ALL", "总配置"),
	MANAGE("MANAGE", "管理类"),
	CLINICAL("CLINICAL", "临床类"),
	ClinicalSkill("ClinicalSkill", "疾病种类/临床技能操作"),
	MedicalEquipment("MedicalEquipment", "医疗设备"),
	TeachingRounds("TeachingRounds", "教学查房考评"),
	CaseRecord("CaseRecord", "病历书写考评"),
	ClinicalCompetence("ClinicalCompetence", "临床能力考评")
	;

	private final String id;
	private final String name;

	EvalCfgEnum(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, EvalCfgEnum.class).getName();
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
