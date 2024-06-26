package com.pinde.sci.enums.gcp;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum GcpFundTypeEnum implements GeneralEnum<String>{
    In("In","到账"),
    Out("Out","支出")
    ;
	
	private final String id;
	private final String name;
	
	
	private GcpFundTypeEnum(String id, String name) {
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	public static String getNameById(String id) {
		return EnumUtil.getById(id, GcpFundTypeEnum.class).getName();
	}
}
