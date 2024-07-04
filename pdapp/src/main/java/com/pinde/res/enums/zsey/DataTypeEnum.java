package com.pinde.res.enums.zsey;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum DataTypeEnum implements GeneralEnum<String> {
	
	Mr("mr","大病历"),
	Disease("disease","病种"),
	Skill("skill","操作技能"),
	Operation("operation","手术"),
	Activity("activity","活动"),
	Summary("summary","出科小结"),
	;

	private final String id;
	private final String name;
	
	DataTypeEnum(String id,String name) {
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
		return EnumUtil.getById(id, DataTypeEnum.class).getName();
	}
}
