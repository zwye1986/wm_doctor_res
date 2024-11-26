package com.pinde.sci.enums.res;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum EvaluationTypeEnum implements GeneralEnum<String> {//需与ResRecTypeEnum同步添加

	DoctorEval("DoctorEval","学员对科室评价表"),
	DeptEval("DeptEval","科室对学员评价表")
	;

	private final String id;
	private final String name;

	EvaluationTypeEnum(String id, String name) {
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
		return EnumUtil.getById(id, EvaluationTypeEnum.class).getName();
	}
}
