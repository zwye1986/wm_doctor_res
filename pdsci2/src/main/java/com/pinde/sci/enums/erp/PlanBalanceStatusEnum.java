package com.pinde.sci.enums.erp;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum PlanBalanceStatusEnum implements GeneralEnum<String> {

	Auditing("Auditing","待确认"),
	AuditBack("AuditBack","已驳回"),
	AuditPass("AuditPass","已确认")
	;

	private final String id;
	private final String name;

	PlanBalanceStatusEnum(String id, String name) {
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
		return EnumUtil.getById(id, PlanBalanceStatusEnum.class).getName();
	}
	
}
