package com.pinde.sci.enums.jszy;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum JszyResAsseAuditListEnum implements GeneralEnum<String> {

	Auditing("Auditing","待基地审核"),
	LocalNotPassed("LocalNotPassed","基地审核不通过"),
	WaitChargePass("WaitChargePass" , "待市局审核"),
	ChargeNotPassed("ChargeNotPassed" , "市局审核不通过"),
	WaitGlobalPass("WaitGlobalPass" , "待省厅审核"),
	GlobalNotPassed("GlobalNotPassed" , "省厅审核不通过"),
	GlobalPassed("GlobalPassed" , "省厅审核通过")
	;

	private final String id;
	private final String name;

	JszyResAsseAuditListEnum(String id, String name) {
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
		return EnumUtil.getById(id, JszyResAsseAuditListEnum.class).getName();
	}
}
