package com.pinde.res.enums.xnres;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum SigninTypeEnum implements GeneralEnum<String> {
	
	Day("Day","每日签到"),
	Month("Month","每月签到"),
	;

	private final String id;
	private final String name;
	


	SigninTypeEnum(String id,String name) {
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
		return EnumUtil.getById(id, SigninTypeEnum.class).getName();
	}
}
