package com.pinde.sci.enums.gzzyjxres;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum StationPropertyEnum implements GeneralEnum<String> {

	InPreparation("InPreparation","编制内"),
	Employ("Employ","聘用"),
	Other("Other" , "其他")
	;

	private final String id;
	private final String name;
	
	StationPropertyEnum(String id, String name) {
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
		return EnumUtil.getById(id, StationPropertyEnum.class).getName();
	}
}
