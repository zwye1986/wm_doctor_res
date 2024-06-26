package com.pinde.sci.enums.erp;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum DealTypeEnum implements GeneralEnum<String> {
	
	Remote("Remote","远程"),
	Visit("Visit","上门")
	;

	private final String id;
	private final String name;
	
	DealTypeEnum(String id,String name) {
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
		return EnumUtil.getById(id, DealTypeEnum.class).getName();
	}
	
}
