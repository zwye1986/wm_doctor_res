package com.pinde.sci.enums.hbzy;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum JszyResTrainYearEnum implements GeneralEnum<String> {
	
	OneYear("1","一年"),
	TwoYear("2","两年"),
	ThreeYear("3","三年"),
	;

	private final String id;
	private final String name;
	


	JszyResTrainYearEnum(String id, String name) {
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
		return EnumUtil.getById(id, JszyResTrainYearEnum.class).getName();
	}
}
