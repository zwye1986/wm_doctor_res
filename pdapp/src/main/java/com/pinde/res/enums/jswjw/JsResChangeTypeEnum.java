package com.pinde.res.enums.jswjw;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum JsResChangeTypeEnum implements GeneralEnum<String>{
	BaseChange("BaseChange","变更基地"),
	SpeChange("SpeChange","变更专业"),
	;
	
	private final String id;
	private final String name;
	
	JsResChangeTypeEnum(String id, String name) {
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
		return EnumUtil.getById(id, JsResChangeTypeEnum.class).getName();
	}


}
