package com.pinde.res.enums.nfyy;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum DeptStatusEnum implements GeneralEnum<String> {
	

	Entering("Entering","已入科"),
	NotEntered("NotEntered","未入科"),
	Exited("Exited","已出科");

	private final String id;
	private final String name;
	
	DeptStatusEnum(String id,String name) {
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
		return EnumUtil.getById(id, DeptStatusEnum.class).getName();
	}
}
