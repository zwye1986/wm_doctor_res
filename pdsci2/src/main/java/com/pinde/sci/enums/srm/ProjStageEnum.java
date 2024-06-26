package com.pinde.sci.enums.srm;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ProjStageEnum implements GeneralEnum<String> {
	
	Apply("Apply","申报阶段"),
	Approve("Approve","立项阶段"),
	Award("Award","发奖阶段"),//'科技报奖'
	Contract("Contract" , "合同阶段"),
	Schedule("Schedule","实施阶段"),
	Complete("Complete","结束阶段"),
	Archive("Archive","归档阶段"),
	;

	private final String id;
	private final String name;
	
	ProjStageEnum(String id,String name) {
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
		return EnumUtil.getById(id, ProjStageEnum.class).getName();
	}
}
