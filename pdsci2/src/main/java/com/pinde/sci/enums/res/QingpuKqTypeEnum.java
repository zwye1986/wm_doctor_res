package com.pinde.sci.enums.res;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum QingpuKqTypeEnum implements GeneralEnum<String> {

	Appeal("Appeal","申诉"),
	Leave("Leave","请假"),
	Submit("Submit","违纪报送")
	;

	private final String id;
	private final String name;

	QingpuKqTypeEnum(String id, String name) {
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
		return EnumUtil.getById(id, QingpuKqTypeEnum.class).getName();
	}
}
