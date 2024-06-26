package com.pinde.sci.enums.czyyjxres;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum TuitionCategoryEnum implements GeneralEnum<String> {

	WorkCloths("WorkCloths","工作服"),
	Deposit("Deposit","押金"),
	Helping("Helping","帮扶进修生"),
	Half("Half ","半年以下每月学费"),
	NotFull("NotFull","满半年不满一年每月学费"),
	More("More","满一年及以上每月学费"),
	Foreign("Foreign","英文进修生每日学费")
	;

	private final String id;
	private final String name;

	TuitionCategoryEnum(String id, String name) {
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
		return EnumUtil.getById(id, TuitionCategoryEnum.class).getName();
	}
}
