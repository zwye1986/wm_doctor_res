package com.pinde.sci.enums.res;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum PersonnelAttributeEnum implements GeneralEnum<String> {

	Local("Local","地方人员"),
	ArmyCivilian("ArmyCivilian","军队文职人员"),
	ArmyActive ("ArmyActive","军队现役军人"),
	;
	private final String id;
	private final String name;



	PersonnelAttributeEnum(String id, String name) {
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
		return EnumUtil.getById(id, PersonnelAttributeEnum.class).getName();
	}
}
