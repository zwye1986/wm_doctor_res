package com.pinde.sci.enums.gyxjgl;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ClassOrderEnumOfGz implements GeneralEnum<String>{

//	First("First","第一节","08：30-09：10"),
//	Second("Second","第二节","09：15-09：55"),
//	Third("Third","第三节","10：05-10：45"),
//	Forth("Forth","第四节","10：50-11：30"),
//	Fifth("Fifth","第五节","11：35-12：15"),
//	Sixth("Sixth","第六节","14：00-14：40"),
//	Seventh("Seventh","第七节","14：45-15：25"),
//	Eighth("Eighth","第八节","15：35-16：15"),
//	Nine("Nine","第九节","16：20-17：00"),
//	Ten("Ten","第十节","18：30-19：10"),
//	Eleven("Eleven","第十一节","19：15-19：55"),
//	Twelve("Twelve","第十二节","20：00-20：40")
	First("First","第一节","08：30-09：10"),
	Second("Second","第二节","09：15-09：55"),
	Third("Third","第三节","10：05-10：45"),
	Forth("Forth","第四节","10：50-11：30"),
	Fifth("Fifth","第五节","11：35-12：15"),
	Sixth("Sixth","第六节","12：20-13：05"),
	Seventh("Seventh","第七节","13：10-13：50"),
	Eighth("Eighth","第八节","14：00-14：40"),
	Nine("Nine","第九节","14：45-15：25"),
	Ten("Ten","第十节","15：35-16：15"),
	Eleven("Eleven","第十一节","16：20-17：00"),
	Twelve("Twelve","第十二节","17：05-17：45"),
	Thirteen("Thirteen","第十三节","17：50-18：30"),
	Fourteen("Fourteen","第十四节","18：30-19：10"),
	Fifteen("Fifteen","第十五节","19：15-19：55"),
	Sixteen("Sixteen","第十六节","20：00-20：40")
	;
	private final String id;
	private final String name;
	private String time;

	ClassOrderEnumOfGz(String id, String name, String time) {
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
		return EnumUtil.getById(id, ClassOrderEnumOfGz.class).getName();
	}
}
