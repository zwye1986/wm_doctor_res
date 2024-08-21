package com.pinde.sci.enums.res;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum HBRecDocTypeEnum implements GeneralEnum<String> {

	Company("Company","本单位人"),
	CompanyEntrust("CompanyEntrust", "委培单位人"),
	//	Social("Social","行业人"),
	Social("Social","社会人"),
	Graduate("Graduate", "四证合一"),
	;
	private final String id;
	private final String name;



	HBRecDocTypeEnum(String id, String name) {
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
		return EnumUtil.getById(id, HBRecDocTypeEnum.class).getName();
	}
}
