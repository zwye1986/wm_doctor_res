package com.pinde.sci.enums.jszy;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum JszyResChangeTypeEnum implements GeneralEnum<String>{
	BaseChange("BaseChange","变更基地"),
	SpeChange("SpeChange","变更专业"),
	;
	
	private final String id;
	private final String name;
	
	JszyResChangeTypeEnum(String id, String name) {
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
		return EnumUtil.getById(id, JszyResChangeTypeEnum.class).getName();
	}


}
