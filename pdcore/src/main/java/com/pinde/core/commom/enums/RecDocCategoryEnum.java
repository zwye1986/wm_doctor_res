package com.pinde.core.commom.enums;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum RecDocCategoryEnum implements GeneralEnum<String> {
	
//	InDoctor("InDoctor","本院规培"),
//	OutDoctor("OutDoctor","外院规培"),
//	FieldTrain("FieldTrain","外地委培"),
	Doctor("Doctor","住院医师"),
	Graduate("Graduate","研究生"),
	Intern("Intern","实习生"),
	Scholar("Scholar","进修生"),
//	EightYear("EightYear","八年制"),
//	UnderGrad("UnderGrad","本科生"),
	Specialist("Specialist","专科医师"),
	GeneralDoctor("GeneralDoctor","培训学员"),
	WMFirst("WMFirst","江苏西医.一阶段"),
	WMSecond("WMSecond","江苏西医.二阶段"),
	AssiGeneral("AssiGeneral","江苏西医.助理全科")
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
