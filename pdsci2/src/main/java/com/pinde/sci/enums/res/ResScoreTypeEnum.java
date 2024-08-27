package com.pinde.sci.enums.res;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ResScoreTypeEnum implements GeneralEnum<String> {
	
	YearScore("YearScore","年度考核"),
	FirstYear("FirstYear","第一年"),
	SecondYear("SecondYear","第二年"),
	ThirdYear("ThirdYear","第三年"),
	GraduationScore("GraduationScore","结业考核"),
	DeptScore("DeptScore","出科考核"),

	TheoryScore("TheoryScore","理论考核"),
	SkillScore("SkillScore","技能考核"),
	PublicScore("PublicScore","公共科目考核")
	;

	private final String id;
	private final String name;


	ResScoreTypeEnum(String id,String name) {
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
		return EnumUtil.getById(id, ResScoreTypeEnum.class).getName();
	}
}
