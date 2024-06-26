package com.pinde.sci.enums.xjgl;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ScholarshipTypeEnum implements GeneralEnum<String> {

	Gjzxj("Gjzxj","国家助学金"),
	Xyzxj("Xyzxj","学业助学金"),
	Zggw("Zggw","助管岗位"),
	Yxyjs("Yxyjs","优秀研究生"),
	Yxyjsgg("Yxyjsgg","优秀研究生骨干"),
	Yxbyyjs("Yxbyyjs","优秀毕业研究生"),
	Gjjxj("Gjjxj","国家奖学金"),
	Gjjxjbcjh("Gjjxjbcjh","国家奖学金补充计划"),
	Xyjxj("Xyjxj","学业奖学金");

	private final String id;
	private final String name;

	ScholarshipTypeEnum(String id, String name) {
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
		return EnumUtil.getById(id, ScholarshipTypeEnum.class).getName();
	}
}
