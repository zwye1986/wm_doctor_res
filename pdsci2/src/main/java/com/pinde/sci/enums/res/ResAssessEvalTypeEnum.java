package com.pinde.sci.enums.res;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ResAssessEvalTypeEnum implements GeneralEnum<String> {

	EvalAll("EvalAll","对总分打分"),
	EvalItem("EvalItem","对子项打分")
	;

	private final String id;
	private final String name;



	ResAssessEvalTypeEnum(String id, String name) {
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
		return EnumUtil.getById(id, ResAssessEvalTypeEnum.class).getName();
	}
}
