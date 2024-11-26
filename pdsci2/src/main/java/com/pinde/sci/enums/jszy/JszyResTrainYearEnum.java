package com.pinde.sci.enums.jszy;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

import java.util.Objects;

public enum JszyResTrainYearEnum implements GeneralEnum<String> {
	
	OneYear("1","一年","OneYear"),
	TwoYear("2","两年","TwoYear"),
	ThreeYear("3","三年","ThreeYear"),
	;

	private final String id;
	private final String name;
	private final String nameEn;
	


	JszyResTrainYearEnum(String id, String name) {
		this.id = id;
		this.name = name;
		this.nameEn = "";
	}

	JszyResTrainYearEnum(String id, String name, String nameEn) {
		this.id = id;
		this.name = name;
		this.nameEn = nameEn;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	public String getNameEn() {
		return nameEn;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, JszyResTrainYearEnum.class).getName();
	}

	public static String getNameByNameEn(String nameEn) {
		JszyResTrainYearEnum[] enumStatus= JszyResTrainYearEnum.values();
		for (JszyResTrainYearEnum sts: enumStatus){
			if (Objects.equals(sts.getNameEn(),nameEn)){
				return sts.getName();
			}
		}
		return "";
	}
}
