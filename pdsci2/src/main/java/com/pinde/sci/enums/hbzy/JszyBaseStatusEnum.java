package com.pinde.sci.enums.hbzy;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum JszyBaseStatusEnum implements GeneralEnum<String> {
	
	NotSubmit("NotSubmit","未提交"),
	Auditing("Auditing","待审核"),
	LocalPassed("LocalPassed","基地审核通过"),
	LocalUnPassed("LocalUnPassed","基地审核不通过"),
	ChargePassed("ChargePassed" , "市局审核通过"),
	ChargeUnPassed("ChargeUnPassed" , "市局审核不通过"),
	GlobalPassed("GlobalPassed" , "省厅审核通过"),
	GlobalUnPassed("GlobalUnPassed" , "省厅审核不通过"),
	Passed("Passed" , "审核通过"),
	NotPassed("NotPassed" , "审核不通过")
	;

	private final String id;
	private final String name;
	
	JszyBaseStatusEnum(String id, String name) {
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
		return EnumUtil.getById(id, JszyBaseStatusEnum.class).getName();
	}
}
