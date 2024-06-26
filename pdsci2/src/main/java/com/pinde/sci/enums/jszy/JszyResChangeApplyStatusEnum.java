package com.pinde.sci.enums.jszy;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum JszyResChangeApplyStatusEnum implements GeneralEnum<String>{
	OutApplyWaiting("OutApplyWaiting","待转出基地审核"),
	OutApplyUnPass("OutApplyUnPass","转出基地审核不通过"),
	InApplyWaiting("InApplyWaiting","待转入基地审核"),
	InApplyUnPass("InApplyUnPass","转入基地审核不通过"),
	GlobalApplyWaiting("GlobalApplyWaiting","待省厅审核"),
	GlobalApplyUnPass("GlobalApplyUnPass","省厅审核不通过"),
	GlobalApplyPass("GlobalApplyPass","省厅审核通过"),
	;
	
	private final String id;
	private final String name;
	
	JszyResChangeApplyStatusEnum(String id, String name) {
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
		return EnumUtil.getById(id, JszyResChangeApplyStatusEnum.class).getName();
	}


}
