package com.pinde.sci.enums.exam;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ExamBankTypeEnum implements GeneralEnum<String> {

	Bank0("0","总题库"),
	Bank1("1","西医分题库"),
	Bank2("2","中医分题库"),
	Bank3("3","护理分题库"),
	Bank4("4","临床医学分题库"),
	Bank5("5","住院医师分题库"),
	Bank6("6","中医住院医师分题库"),
	Bank15("15","住院医师分题库2"),
	Bank16("16","中医住院医师分题库2"),
	Bank20("20","住培年度考试"),
	Bank21("21","二阶段结业考试"),
	Bank22("22","图片题库"),
	Bank30("30","临时库"),
	Bank90("90","I医考分题库"),

	Bank101("101","第四军医大学附属唐都医院"),
	Bank102("102","四川中医结业"),
	Bank103("103","武警后勤学院附属医院"),
	Bank104("104","苏州中西医结合医院")
	;

	private final String id;
	private final String name;

	ExamBankTypeEnum(String id,String name) {
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
		return EnumUtil.getById(id, ExamBankTypeEnum.class).getName();
	}
}
