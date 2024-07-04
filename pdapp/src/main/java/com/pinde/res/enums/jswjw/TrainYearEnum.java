package com.pinde.res.enums.jswjw;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum TrainYearEnum implements GeneralEnum<String> {
	
	OneYear("OneYear","一年"),
	TwoYear("TwoYear","两年"),
	ThreeYear("ThreeYear","三年"),
	;

	private final String id;
	private final String name;
	


	TrainYearEnum(String id,String name) {
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
		return EnumUtil.getById(id,TrainYearEnum.class).getName();
	}
}
