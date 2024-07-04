package com.pinde.res.enums.sctcm120;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum RotationStatusEnum implements GeneralEnum<String> {
	OverDue("OverDue","延期"),
	Rounding("Rounding","轮转中"),
	NotDue("NotDue","未到入科时间")
	;

	private final String id;
	private final String name;

	RotationStatusEnum(String id, String name) {
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
		return EnumUtil.getById(id, RotationStatusEnum.class).getName();
	}
}
