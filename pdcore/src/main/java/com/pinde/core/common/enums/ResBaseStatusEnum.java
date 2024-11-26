package com.pinde.core.common.enums;

import com.pinde.core.util.EnumUtil;

public enum ResBaseStatusEnum implements GeneralEnum<String> {
	
	NotSubmit("NotSubmit","未提交"),
	Auditing("Auditing","待审核"),
	ChargePassed("ChargePassed" , "市局审核通过"),
    ChargeAudit("ChargeAudit", "待市局审核"),
    ChargeNotPassed("ChargeNotPassed", "市局审核不通过"),
	GlobalPassed("GlobalPassed" , "省厅审核通过"),
	GlobalNotPassed("GlobalNotPassed" , "省厅审核不通过"),
	Passed("Passed" , "审核通过"),
	NotPassed("NotPassed" , "审核不通过")
	;

	private final String id;
	private final String name;

    ResBaseStatusEnum(String id, String name) {
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
		return EnumUtil.getById(id, ResBaseStatusEnum.class).getName();
	}
}
