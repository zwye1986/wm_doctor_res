package com.pinde.sci.enums.pub;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;
import com.pinde.core.util.StringUtil;

public enum UserDegreeSubjectEnum implements GeneralEnum<String> {

	Zx("Zx", "哲学"),
	Lljjx("Lljjx", "理论经济学"),
	Yyjjx("Yyjjx", "应用经济学"),
	Fx("Fx", "法学"),
	Zzx("Zzx", "政治学"),
	Shx("Shx", "社会学"),
	Mkszyll("Mkszyll", "马克思主义理论"),
	Jyx("Jyx", "教育学"),
	Xlx("Xlx", "心理学"),
	Tux("Tux", "体育学"),
	Zgyywx("Zgyywx", "中国语言文学"),
	Wgyywx("Wgyywx", "外国语言文学"),
	Xwcbx("Xwcbx", "新闻传播学"),
	Zgs("Zgs", "中国史"),
	Sjs("Sjs", "世界史"),
	Sx("Sx", "数学"),
	Wlx("Wlx", "物理学"),
	Hx("Hx", "化学"),
	Swx("Swx", "生物学"),
	Xtkx("Xtkx", "系统科学"),
	Kxjss("Kxjss", "科学技术史"),
	Stx("Stx", "生态学"),
	Tjx("Tjx", "统计学"),
	Yqkxyjs("Yqkxyjs", "仪器科学与技术"),
	Clkxygc("Clkxygc", "材料科学与工程"),
	Dzkxyjs("Dzkxyjs", "电子科学与技术"),
	Xxytxgc("Xxytxgc", "信息与通信工程"),
	Kzkxygc("Kzkxygc", "控制科学与工程"),
	Jsjkxyjs("Jsjkxyjs", "计算机科学与技术"),
	Hxgcyjs("Hxgcyjs", "化学工程与技术"),
	Hkxyjs("Hkxyjs", "核科学与技术"),
	Nygc("Nygc", "农业工程"),
	Lygc("Lygc", "林业工程"),
	Swyxgc("Swyxgc", "生物医学工程"),
	Spkxygc("Spkxygc", "食品科学与工程"),
	Rjgc("Rjgc", "软件工程"),
	Swgc("Swgc", "生物工程"),
	Aqkxygc("Aqkxygc", "安全科学与工程"),
	Zwx("Zwx", "作物学"),
	Nyzyyhj("Nyzyyhj", "农业资源与环境"),
	Zwbh("Zwbh", "植物保护"),
	Cmx("Cmx", "畜牧学"),
	Syx("Syx", "兽医学"),
	Jcyx("Jcyx", "基础医学"),
	Lcyx("Lcyx", "临床医学"),
	Kqyx("Kqyx", "口腔医学"),
	Ggwsyyfyx("Ggwsyyfyx", "公共卫生与预防医学"),
	Zyix("Zyix", "中医学"),
	Zxyjh("Zxyjh", "中西医结合"),
	Yx("Yx", "药学"),
	Zyaox("Zyaox", "中药学"),
	Tzyx("Tzyx", "特种医学"),
	Yxjs("Yxjs", "医学技术"),
	Hlx("Hlx", "护理学"),
	Jshqx("Jshqx", "军事后勤学"),
	Jszbx("Jszbx", "军事装备学"),
	Glkxygc("Glkxygc", "管理科学与工程"),
	Gsgl("Gsgl", "工商管理"),
	Nljjgl("Nljjgl", "农林经济管理"),
	Gggl("Gggl", "公共管理"),
	Tsgqbdagl("Tsgqbdagl", "图书馆、情报与档案管理"),
	Ysxll("Ysxll", "艺术学理论")
	;

	private final String id;
	private final String name;

	UserDegreeSubjectEnum(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, UserDegreeSubjectEnum.class).getName();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	public static String getIdByName(String name) {
		name = StringUtil.defaultString(name).trim();
		UserDegreeSubjectEnum[] values = UserDegreeSubjectEnum.values();
		for (UserDegreeSubjectEnum item : values) {
			if (item.getName().equals(name)) {
				return item.getId();
			}
		}
		return null;
	}
}
