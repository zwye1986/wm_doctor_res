package com.pinde.sci.enums.sczyres;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum SpeCatEnum implements GeneralEnum<String> {
	ChineseMedicine("ChineseMedicine","中医"),
	TCMGeneral("TCMGeneral","中医全科"),
	TCMAssiGeneral("TCMAssiGeneral","中医助理全科")
	;

	private final String id;
	private final String name;
	
	SpeCatEnum(String id,String name) {
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
		return EnumUtil.getById(id, SpeCatEnum.class).getName();
	}
}
