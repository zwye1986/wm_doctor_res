package com.pinde.res.enums.hbres;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;
import com.pinde.core.util.StringUtil;

public enum WorkLogTypeEnum implements GeneralEnum<String> {
	

	Attendance("Attendance","出勤"),
	Absence("Absence","请假"),
	Rest("Rest","休息"),
	;

	private final String id;
	private final String name;
	
	WorkLogTypeEnum(String id,String name) {
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
		return EnumUtil.getById(id, WorkLogTypeEnum.class).getName();
	}

	public static String getIdByName(String name) {
		name = StringUtil.defaultString(name).trim();
	    WorkLogTypeEnum[] values = WorkLogTypeEnum.values();
		for (WorkLogTypeEnum item : values) {
            if (item.getName().equals(name)) {
                return item.getId();
            }
		}
		return null;
	}
	
}
