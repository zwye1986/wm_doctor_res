package com.pinde.core.common.enums;

import com.pinde.core.util.EnumUtil;

public enum JsResChangeApplyStatusEnum implements GeneralEnum<String>{
	OutApplyWaiting("OutApplyWaiting","待转出审核"),
	OutApplyUnPass("OutApplyUnPass","转出审核不通过"),
	InApplyWaiting("InApplyWaiting","待转入审核"),
	InApplyPass("InApplyPass","转入审核通过"),
	InApplyUnPass("InApplyUnPass","转入审核不通过"),
	;
	
	private final String id;
	private final String name;
	
	JsResChangeApplyStatusEnum(String id,String name) {
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
		return EnumUtil.getById(id, JsResChangeApplyStatusEnum.class).getName();
	}


}
