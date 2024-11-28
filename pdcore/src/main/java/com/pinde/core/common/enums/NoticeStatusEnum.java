package com.pinde.core.common.enums;

import com.pinde.core.common.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum NoticeStatusEnum implements GeneralEnum<String> {

	IsRead("IsRead","已读"),
	NoRead("NoRead","未读")
	;

	private final String id;
	private final String name;



	NoticeStatusEnum(String id, String name) {
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
		return EnumUtil.getById(id, NoticeStatusEnum.class).getName();
	}
}
