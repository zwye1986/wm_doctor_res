package com.pinde.sci.enums.sch;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum SchArrangeStatusEnum implements GeneralEnum<String> {
	Process("Process", "排班中"),
	Finish("Finish", "排班结束"),
	Aborting("Aborting", "终止中"),
	Aborted("Aborted", "已终止"),
	Confirm("Confirm", "排班确认"),
	;

	private final String id;
	private final String name;
	
	SchArrangeStatusEnum(String id,String name) {
		this.id = id;
		this.name = name;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, SchArrangeStatusEnum.class).getName();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
}
