package com.pinde.sci.enums.erp;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ContractStatusEnum implements GeneralEnum<String> {
	
	Auditing("Auditing","待审核"),
	AuditBack("AuditBack","审核退回"),
	Implement("Implement","执行中"),
	Finish("Finish","执行完毕"),
	Terminate("Terminate","暂停"),
	End("End","终止")
	;

	private final String id;
	private final String name;
	
	ContractStatusEnum(String id,String name) {
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
		return EnumUtil.getById(id, ContractStatusEnum.class).getName();
	}
	
}
