package com.pinde.core.common.enums;

import com.pinde.core.util.EnumUtil;

public enum ResDoctorAuditStatusEnum implements GeneralEnum<String> {
	
	NotSubmit("NotSubmit","未提交"),
	Auditing("Auditing","待审核"),
//	LocalPassed("LocalPassed" , "基地审核通过"),
//	ChargePassed("ChargePassed" , "市局审核通过"),
//	GlobalPassed("GlobalPassed" , "省厅审核通过"),
	Passed("Passed" , "审核通过"),
	NotPassed("NotPassed" , "审核不通过")
	;
	private final String id;
	private final String name;
	
	ResDoctorAuditStatusEnum(String id,String name) {
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
		return EnumUtil.getById(id, ResDoctorAuditStatusEnum.class).getName();
	}
}
