package com.pinde.res.enums.stdp;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum TrainYearEnum implements GeneralEnum<Integer> {
	
	OneYear(1,"一年"),
	TwoYear(2,"两年"),
	ThreeYear(3,"三年"),
	;

	private final Integer id;
	private final String name;
	


	TrainYearEnum(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
	public static String getNameById(String id) {
		return EnumUtil.getById(id, TrainYearEnum.class).getName();
	}
}
