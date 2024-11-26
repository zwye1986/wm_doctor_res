package com.pinde.sci.enums.res;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum StuStatusEnum implements GeneralEnum<String> {
	UnSubmit("UnSubmit","未提交"),
	Passing("Passing","待审核"),
	HeadPassing("HeadPassing","待科室审核"),
	Passed("Passed","报名审核通过"),
	HeadPassed("HeadPassed","科主任审核通过"),
	UnHeadPassed("UnHeadPassed","科主任审核不通过"),
	UnPassed("UnPassed","报名审核不通过"),
	Recruitted("Recruitted","已录取"),
	UnRecruitted("UnRecruitted","不录取"),
	Admited("Admited","已报到"),
	UnAdmitd("UnAdmitd","未报到"),
	Graduation("Graduation","已结业"),
	DelayGraduation("DelayGraduation","延期结业");

	private final String id;
	private final String name;

	StuStatusEnum(String id, String name) {
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
		return EnumUtil.getById(id, StuStatusEnum.class).getName();
	}
}
