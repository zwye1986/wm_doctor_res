package com.pinde.sci.enums.erp;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum CompanyNameEnum implements GeneralEnum<String> {
	
	PX("PX","南京品德网络信息技术有限公司"),
	PK("PK","南京品德科技有限责任公司")
	;

	private final String id;
	private final String name;
	
	CompanyNameEnum(String id,String name) {
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
		return EnumUtil.getById(id, CompanyNameEnum.class).getName();
	}
}
