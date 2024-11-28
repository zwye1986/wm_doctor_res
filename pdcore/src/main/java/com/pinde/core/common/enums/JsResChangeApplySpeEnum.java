package com.pinde.core.common.enums;

import com.pinde.core.common.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum JsResChangeApplySpeEnum implements GeneralEnum<String> {
	BaseWaitingAudit("BaseWaitingAudit","基地待审核"),
	BaseAuditUnPass("BaseAuditUnPass","基地审核不通过"),
    BaseAuditPass("BaseAuditPass", "基地审核通过"),//当学员仅变更二级专业时，不需要省厅审核
	GlobalWaitingAudit("GlobalWaitingAudit","省厅待审核"),
	GlobalAuditPass("GlobalAuditPass","省厅审核通过"),
	GlobalAuditunPass("GlobalAuditunPass","省厅审核不通过"),
	;
	
	private final String id;
	private final String name;
	
	JsResChangeApplySpeEnum(String id,String name) {
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
        return EnumUtil.getById(id, com.pinde.core.common.enums.JsResChangeApplySpeEnum.class).getName();
	}


}
