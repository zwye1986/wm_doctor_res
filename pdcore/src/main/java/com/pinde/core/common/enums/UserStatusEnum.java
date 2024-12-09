package com.pinde.core.common.enums;

import com.pinde.core.common.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum UserStatusEnum implements GeneralEnum<String> {
	Added("Added","新增"),
	Reged("Reged","待审核"),
	Locked("Locked", "停用"),
	SysLocked("SysLocked", "锁定"),
	Activated("Activated","已激活"),
	Lifted("Lifted", "解除使用"),
	;

	private final String id;
	private final String name;
	
	UserStatusEnum(String id,String name) {
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
		return EnumUtil.getById(id, UserStatusEnum.class).getName();
	}
}
