package com.pinde.sci.enums.jsres;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ResTeachQualifiedStatusEnum implements GeneralEnum<String> {

	Staging("Staging","暂存"),
//	Publish("Publish","发布"),
	NotStarted("NotStarted","未开始"),
	Enrolling("Enrolling" , "报名中"),
	RegistrationEnded("RegistrationEnded" , "报名结束"),
	Intraining("Intraining" , "培训中"),
	EndOfTraining("EndOfTraining" , "培训结束")
	;

	private final String id;
	private final String name;

	ResTeachQualifiedStatusEnum(String id, String name) {
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
		return EnumUtil.getById(id, ResTeachQualifiedStatusEnum.class).getName();
	}
}
