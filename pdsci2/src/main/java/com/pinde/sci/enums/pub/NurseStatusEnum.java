package com.pinde.sci.enums.pub;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum NurseStatusEnum implements GeneralEnum<String> {
	Locked("Locked", "锁定"),
	Activated("Activated", "已激活"),
	;

	private final String id;
	private final String name;

	NurseStatusEnum(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, NurseStatusEnum.class).getName();
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
