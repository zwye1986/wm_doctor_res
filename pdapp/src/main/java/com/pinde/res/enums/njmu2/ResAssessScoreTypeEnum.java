package com.pinde.res.enums.njmu2;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ResAssessScoreTypeEnum implements GeneralEnum<String> {
	
	Percentile("Percentile","百分制"),
	Nine("Nine","九分制"),
	;

	private final String id;
	private final String name;
	


	ResAssessScoreTypeEnum(String id,String name) {
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
		return EnumUtil.getById(id, ResAssessScoreTypeEnum.class).getName();
	}
}
