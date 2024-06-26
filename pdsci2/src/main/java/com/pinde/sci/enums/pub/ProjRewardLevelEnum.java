package com.pinde.sci.enums.pub;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ProjRewardLevelEnum implements GeneralEnum<String>{

	SpecialReward("SpecialReward", "特等奖"),
	FirstReward("FirstReward", "一等奖"),
	SecondReward("SecondReward", "二等奖"),
	;
	private final String id;
	private final String name;
	
	ProjRewardLevelEnum(String id,String name) {
		this.id = id;
		this.name = name;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, ProjRewardLevelEnum.class).getName();
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
