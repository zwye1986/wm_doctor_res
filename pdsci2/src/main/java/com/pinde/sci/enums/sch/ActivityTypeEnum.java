package com.pinde.sci.enums.sch;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ActivityTypeEnum implements GeneralEnum<String> {
	Ryjy("17","入院教育"),
	Jxcf("1", "教学查房"),
	Jxbltl("11","教学病例讨论"),
	Xsjz("4", "临床小讲课"),
	Lcczjnzd("12","临床操作技能床旁教学"),
	Lcblsxzd("13","住院病历书写指导教学"),
	Rzyjdjy("18","入专业基地教育"),
	Rkjy("6", "入轮转科室教育"),
	Ssczzd("14","手术操作指导教学"),
	Lcwxyd("16","临床文献研读会"),
	Yph("9","教学阅片"),
	Yxzdbgsxzd("15","影像诊断报告书写指导教学"),
	Jxhz("10","门诊教学"),
	Cjbg("19","晨间报告"),
	Ckks("7", "出科考核"),
	Jnpx("8","技能培训"),
	Ynbltl("2", "疑难病例讨论"),
	Wzbltl("3", "危重病例讨论"),
	Swbltl("5", "死亡病例讨论"),
	Bgdfx("20", "报告单分析"),
	Jxsj("21", "教学上机"),
	Sjys("22", "上机演示"),
	;

	private final String id;
	private final String name;

	ActivityTypeEnum(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, ActivityTypeEnum.class).getName();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
}
