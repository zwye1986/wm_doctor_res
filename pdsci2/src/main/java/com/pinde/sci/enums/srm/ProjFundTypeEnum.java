package com.pinde.sci.enums.srm;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ProjFundTypeEnum implements GeneralEnum<String> {
	
	Income("Income","到账"),
	Reimburse("Reimburse","报销"),
	ManageFee("ManageFee","管理费"),
	Budget("Budget","预算"),
	Surplus("Surplus","结余"),
	OwnOutlay("OwnOutlay","个人经费支出"),
	HospitalOutlay("HospitalOutlay","医院经费支出")
	;

	private final String id;
	private final String name;
	
	ProjFundTypeEnum(String id,String name) {
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
		return EnumUtil.getById(id, ProjFundTypeEnum.class).getName();
	}
}
