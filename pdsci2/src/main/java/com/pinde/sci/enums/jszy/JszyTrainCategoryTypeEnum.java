package com.pinde.sci.enums.jszy;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum JszyTrainCategoryTypeEnum implements GeneralEnum<String> {
	
	BeforeCfgDate("BeforeCfgDate","配置日期之前培训类别"),
	AfterCfgDate("AfterCfgDate","配置日期之后培训类别"),
	Independent("Independent","培训类别")
	;

	private final String id;
	private final String name;
	


	JszyTrainCategoryTypeEnum(String id, String name) {
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
		return EnumUtil.getById(id, JszyTrainCategoryTypeEnum.class).getName();
	}
}
