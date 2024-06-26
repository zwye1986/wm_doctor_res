package com.pinde.sci.enums.jsres;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum JsResTrainYearEnum implements GeneralEnum<String> {
	
	OneYear("OneYear","一年"),
	TwoYear("TwoYear","两年"),
	ThreeYear("ThreeYear","三年"),
	;

	private final String id;
	private final String name;
	


	JsResTrainYearEnum(String id,String name) {
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
		if (EnumUtil.getById(id, JsResTrainYearEnum.class) != null) {
			return EnumUtil.getById(id, JsResTrainYearEnum.class).getName();
		}else{
			return "";
		}

	}
}
