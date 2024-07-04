package com.pinde.res.enums.sctcm120;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum AfterRecTypeEnum implements GeneralEnum<String> {
	
	DOPS("DOPS","临床操作技能评估量化表"),
	Mini_CEX("Mini_CEX","迷你临床演练评估量化表"),
	AfterEvaluation("AfterEvaluation","出科考核表"),
	AfterSummary("AfterSummary","出科小结"),
	DiscipleSummary("DiscipleSummary","门诊跟师小结")
	;

	private final String id;
	private final String name;

	AfterRecTypeEnum(String id, String name) {
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
		return EnumUtil.getById(id, AfterRecTypeEnum.class).getName();
	}
}
