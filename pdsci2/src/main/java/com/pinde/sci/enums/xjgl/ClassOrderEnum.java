package com.pinde.sci.enums.xjgl;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ClassOrderEnum implements GeneralEnum<String>{

	First("First","一","8：00-8：40"),
	Second("Second","二","8：45-9：25"),
	Third("Third","三","9：50-10：30"),
	Forth("Forth","四","10：35-11：15"),
	Fifth("Fifth","五","11：20-12：00"),
	Sixth("Sixth","六","14：30-15：10"),
	Seventh("Seventh","七","15：15-16：05"),
	Eighth("Eighth","八","16：15-16：55"),
	Ninth("Ninth","九","17：00-17：40"),
	Night("Night","晚上","19：00-21：10")
	;
	private final String id;
	private final String name;
	private String time;

	ClassOrderEnum(String id, String name,String time) {
		this.id = id;
		this.name = name;
		this.time = time;
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	public String getTime() {
		return time;
	}
	
	public static String getNameById(String id) {
		return EnumUtil.getById(id, ClassOrderEnum.class).getName();
	}
}
