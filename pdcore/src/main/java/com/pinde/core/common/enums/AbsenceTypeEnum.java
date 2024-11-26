package com.pinde.core.common.enums;

import com.pinde.core.util.EnumUtil;

public enum AbsenceTypeEnum implements GeneralEnum<String> {
	
	Leave("Leave","事假","04"),//最后数字为中山二院考勤编码
	Sickleave("Sickleave","病假","02"),
	Maternityleave("Maternityleave","产假","11"),
	Marriage("Marriage","婚假","10"),
	Yearleave("Yearleave","年假","06"),
	Absenteeism("Absenteeism","旷工","20")
	;

	private final String id;
	private final String name;
	private final String code;


	AbsenceTypeEnum(String id,String name,String code) {
		this.id = id;
		this.name = name;
		this.code = code;
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
		return EnumUtil.getById(id, AbsenceTypeEnum.class).getName();
	}
//	public static String getCodeById(String id) {
//		return EnumUtil.getById(id, AbsenceTypeEnum.class).getCode();
//	}
	public String getCode() {
		return code;
	}
}
