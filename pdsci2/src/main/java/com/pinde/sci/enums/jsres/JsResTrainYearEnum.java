package com.pinde.sci.enums.jsres;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum JsResTrainYearEnum implements GeneralEnum<String> {
	
	OneYear("OneYear","一年", 1),
	TwoYear("TwoYear","两年", 2),
	ThreeYear("ThreeYear","三年", 3),
	;

	private final String id;
	private final String name;
	private final Integer years;

	JsResTrainYearEnum(String id,String name, Integer years) {
		this.id = id;
		this.name = name;
		this.years = years;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	public Integer getYears() {
		return years;
	}
	public static String getNameById(String id) {
		if (EnumUtil.getById(id, JsResTrainYearEnum.class) != null) {
			return EnumUtil.getById(id, JsResTrainYearEnum.class).getName();
		}else{
			return "";
		}

	}

	public static Integer getYearsById(String id) {
		for(JsResTrainYearEnum resTrainYearEnum: JsResTrainYearEnum.values()) {
			if(resTrainYearEnum.id.equals(id)) {
				return resTrainYearEnum.getYears();
			}
		}

		return null;
	}
}
