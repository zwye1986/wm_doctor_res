package com.pinde.sci.enums.erp;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum UserChangeStatusEnum implements GeneralEnum<String> {

	Applying("Applying","申请中"),
	ApplyBack("ApplyBack","申请驳回"),
	ApplyPass("ApplyPass","申请通过")
	;

	private final String id;
	private final String name;

	UserChangeStatusEnum(String id, String name) {
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
		return EnumUtil.getById(id, UserChangeStatusEnum.class).getName();
	}
	
}
