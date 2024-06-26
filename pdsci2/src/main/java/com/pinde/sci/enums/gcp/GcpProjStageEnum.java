package com.pinde.sci.enums.gcp;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum GcpProjStageEnum implements GeneralEnum<String> {
	Apply("Apply","准备阶段"),
	Schedule("Schedule","实施阶段"),
	Terminate("Terminate","终止"),
	Complete("Complete","结束阶段")
	;

	private final String id;
	private final String name;
	
	GcpProjStageEnum(String id,String name) {
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
		return EnumUtil.getById(id, GcpProjStageEnum.class).getName();
	}
}
