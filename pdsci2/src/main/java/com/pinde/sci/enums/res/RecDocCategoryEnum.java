package com.pinde.sci.enums.res;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum RecDocCategoryEnum implements GeneralEnum<String> {
	
//	InDoctor("InDoctor","本院规培"),
//	OutDoctor("OutDoctor","外院规培"),
//	FieldTrain("FieldTrain","外地委培"),
	Doctor("Doctor","住院医师"),
	Graduate("Graduate","研究生"),
	Intern("Intern","实习生"),
	Scholar("Scholar","进修生"),
	AssistantGeneral("AssistantGeneral","助理全科"),
//	EightYear("EightYear","八年制"),
//	UnderGrad("UnderGrad","本科生"),
	Specialist("Specialist","专科医师"),
	GeneralDoctor("GeneralDoctor","培训学员"),
	WMFirst("WMFirst","江苏西医.一阶段"),
	WMSecond("WMSecond","江苏西医.二阶段"),
	AssiGeneral("AssiGeneral","江苏西医.助理全科"),
	ChineseMedicine("ChineseMedicine","中医"),
	TCMGeneral("TCMGeneral","中医全科"),
	TCMAssiGeneral("TCMAssiGeneral","中医助理全科"),
	GeneralPractitioner("GeneralPractitioner","全科医师"),
	AssistantGeneralDoc("AssistantGeneralDoc","助理全科医生"),
	GPractitionerTransf("GPractitionerTransf","全科医生转岗"),
	GPractitionerStat("GPractitionerStat","全科医生岗位"),
	PediatricianTransf("PediatricianTransf","儿科医师转岗"),
	ObstetricianTransf("ObstetricianTransf","产科医师转岗")
	;
	private final String id;
	private final String name;
	


	RecDocCategoryEnum(String id,String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
	public static String getNameById(String id) {
		return EnumUtil.getById(id, RecDocCategoryEnum.class).getName();
	}
}
