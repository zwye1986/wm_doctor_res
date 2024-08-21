package com.pinde.sci.enums.test;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum PaperTypeEnum implements GeneralEnum<String> {

	Simulate("0", "模拟"),
	Random("1", "随机"),
	Invariant("2", "固定"),
	;

	private final String id;
	private final String name;
	
	PaperTypeEnum(String id,String name) {
		this.id = id;
		this.name = name;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, PaperTypeEnum.class).getName();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
}
