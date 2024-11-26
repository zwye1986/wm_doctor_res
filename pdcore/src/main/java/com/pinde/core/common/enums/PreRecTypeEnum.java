package com.pinde.core.common.enums;

import com.pinde.core.util.EnumUtil;

public enum PreRecTypeEnum implements GeneralEnum<String> {//需与ResRecTypeEnum同步添加
	
	PreTrainForm("PreTrainForm","岗前培训表"),
	InProcessInfo("InProcessInfo","入科教育"),
	;

	private final String id;
	private final String name;

	PreRecTypeEnum(String id, String name) {
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
		return EnumUtil.getById(id, PreRecTypeEnum.class).getName();
	}
}
