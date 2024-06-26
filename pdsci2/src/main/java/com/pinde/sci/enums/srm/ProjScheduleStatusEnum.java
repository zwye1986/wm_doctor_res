package com.pinde.sci.enums.srm;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ProjScheduleStatusEnum implements GeneralEnum<String> {
	
	Apply("Apply","填写"),
	Submit("Submit","提交"),
	FirstAudit("FirstAudit","承担单位审核通过"),
	FirstBack("FirstBack","承担单位退回"),
	SecondAudit("SecondAudit","主管部门审核通过"),
	SecondBack("SecondBack","主管部门退回"),
	ThirdAudit("ThirdAudit","审核通过"),
	ThirdBack("ThirdBack","退回"),
	;

	private final String id;
	private final String name;
	
	ProjScheduleStatusEnum(String id,String name) {
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
		return EnumUtil.getById(id, ProjScheduleStatusEnum.class).getName();
	}
}
