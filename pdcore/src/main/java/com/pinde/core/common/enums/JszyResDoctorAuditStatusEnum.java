package com.pinde.core.common.enums;

import com.pinde.core.util.EnumUtil;

public enum JszyResDoctorAuditStatusEnum implements GeneralEnum<String> {
	
	NotSubmit("NotSubmit","未提交"),
	Auditing("Auditing","待审核"),
	Passed("Passed" , "审核通过"),
	NotPassed("NotPassed" , "审核不通过")
	;
	private final String id;
	private final String name;
	
	JszyResDoctorAuditStatusEnum(String id, String name) {
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
		return EnumUtil.getById(id, JszyResDoctorAuditStatusEnum.class).getName();
	}
}
