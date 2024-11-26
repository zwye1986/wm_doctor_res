package com.pinde.core.common.enums;

import com.pinde.core.util.EnumUtil;

public enum AbsenceTypeEnum implements GeneralEnum<String> {
	
	Leave("Leave","事假","04"),//最后数字为中山二院考勤编码
	Sickleave("Sickleave","病假","02"),
	Maternityleave("Maternityleave","产假","11"),
	Marriage("Marriage","婚假","10"),
	Yearleave("Yearleave","年假","06"),
    Retake("Retake", "补休", "08"),
    Maternity("Maternity", "产假", "11"),
    Paternity("Paternity ", "陪产假", "12"),
    PlannedBirth("PlannedBirth", "计生假", "13"),
    Funeral("Funeral", "丧假", "14"),
    GoAbroad("GoAbroad", "出国", "15"),
    Study("Study", "进修", "16"),
    FulltimeGraduate("FulltimeGraduate", "脱产读研", "18"),
    Radiation("Radiation", "放射假", "19"),
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
