package com.pinde.sci.enums.gyxjgl;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum UserAwardApplyTypeEnum implements GeneralEnum<String>{

	PoorArchives("PoorArchives","贫困档案"),
	AGrant("AGrant","助学金"),
	DiligentStudy("DiligentStudy","勤工助学"),
	PovertyAid("PovertyAid","贫困资助"),
	StudentLoan("StudentLoan","助学贷款"),
	TuitionDelay("TuitionDelay","学杂费缓交"),
	Scholarship("Scholarship","奖学金"),
	OtherApply("OtherApply","其他")
	;
	private final String id;
	private final String name;

	UserAwardApplyTypeEnum(String id, String name) {
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
		return EnumUtil.getById(id, UserAwardApplyTypeEnum.class).getName();
	}
}
