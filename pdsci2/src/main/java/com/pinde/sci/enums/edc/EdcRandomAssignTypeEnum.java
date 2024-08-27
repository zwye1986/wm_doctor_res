package com.pinde.sci.enums.edc;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum EdcRandomAssignTypeEnum implements GeneralEnum<String> {
	
	Net("Net","网络申请"),
	App("App","App申请"),
	Sms("Sms","短信申请"),
	;

	private final String id;
	private final String name;
	
	EdcRandomAssignTypeEnum(String id,String name) {
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
		return EnumUtil.getById(id, EdcRandomAssignTypeEnum.class).getName();
	}
}
