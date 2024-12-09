package com.pinde.core.common.enums.sys;

import com.pinde.core.common.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum OperTypeEnum implements GeneralEnum<String> {
	
	LogIn("LogIn","登录"),
	LogOut("LogOut","退出"),
	Course("Course","课程"),
	LogInsert("Insert","新增"),
	LogDelete("Delete","删除"),
	LogUpdate("Update","修改")
	;

	private final String id;
	private final String name;
	
	OperTypeEnum(String id,String name) {
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
		return EnumUtil.getById(id, OperTypeEnum.class).getName();
	}
}
