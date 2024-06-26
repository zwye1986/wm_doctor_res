package com.pinde.sci.enums.srm;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum EvaluationStatusEnum implements GeneralEnum<String> {
	
	Save("Save","保存"),
	Submit("Submit","提交")
	;

	private final String id;
	private final String name;
	
	EvaluationStatusEnum(String id,String name) {
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
		return EnumUtil.getById(id, EvaluationStatusEnum.class).getName();
	}
}
