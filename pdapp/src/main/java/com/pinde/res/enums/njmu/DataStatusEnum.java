package com.pinde.res.enums.njmu;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum DataStatusEnum implements GeneralEnum<String> {

	Saved("Saved","未审核"),
	Rejected("Rejected","否决"),
	Returned("Returned","退回修改"),
	Audited("Audited","已审核"),
	;

	private final String id;
	private final String name;
	
	DataStatusEnum(String id,String name) {
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
		return EnumUtil.getById(id, DataStatusEnum.class).getName();
	}
}
