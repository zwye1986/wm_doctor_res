package com.pinde.sci.enums.pub;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;
import com.pinde.core.util.StringUtil;

public enum UserDegreeEnum implements GeneralEnum<String> {

	ZxBs("ZxBs", "哲学博士"),
	JjxBs("JjxBs", "经济学博士"),
	FxBs("FxBs", "法学博士"),
	JyxBs("JyxBs", "教育学博士"),
	WxBs("WxBs", "文学博士"),
	LsxBs("LsxBs", "历史学博士"),
	LxBs("LxBs", "理学博士"),
	GxBs("GxBs", "工学博士"),
	NxBs("NxBs", "农学博士"),
	YxBs("YxBs", "医学博士"),
	JsxBs("JsxBs", "军事学博士"),
	GlxBs("GlxBs", "管理学博士"),
	YsxBs("YsxBs", "艺术学博士"),
	JyBs("JyBs", "教育博士"),
	GcBs("GcBs", "工程博士"),
	SyBs("SyBs", "兽医博士"),
	LcyxBs("LcyxBs", "临床医学博士"),
	KqyxBs("KqyxBs", "口腔医学博士"),
	ZxSs("ZxSs", "哲学硕士"),
	JjxSs("JjxSs", "经济学硕士"),
	FxSs("FxSs", "法学硕士"),
	JyxSs("JyxSs", "教育学硕士"),
	WxSs("WxSs", "文学硕士"),
	LsxSs("LsxSs", "历史学硕士"),
	LxSs("LxSs", "理学硕士"),
	GxSs("GxSs", "工学硕士"),
	NxSs("NxSs", "农学硕士"),
	YixSs("YixSs", "医学硕士"),
	JsxSs("JsxSs", "军事学硕士"),
	GlxSs("GlxSs", "管理学硕士"),
	YsxSs("YsxSs", "艺术学硕士"),
	YytjSs("YytjSs", "应用统计硕士"),
	SwSs("SwSs", "税务硕士"),
	GjswSs("GjswSs", "国际商务硕士"),
	BxSs("BxSs", "保险硕士"),
	ZcpgSs("ZcpgSs", "资产评估硕士"),
	SjSs("SjSs", "审计硕士"),
	FlSs("FlSs", "法律硕士"),
	ShgzSs("ShgzSs", "社会工作硕士"),
	JySs("JySs", "教育硕士"),
	TySs("TySs", "体育硕士"),
	HygjjjSs("HygjjjSs", "汉语国际教育硕士"),
	YyxlSs("YyxlSs", "应用心理硕士"),
	FySs("FySs", "翻译硕士"),
	XwycbSs("XwycbSs", "新闻与传播硕士"),
	GcSs("GcSs", "工程硕士"),
	NytgSs("NytgSs", "农业推广硕士"),
	SySs("SySs", "兽医硕士"),
	LySs("LySs", "林业硕士"),
	LcyxSs("LcyxSs", "临床医学硕士"),
	KqyxSs("KqyxSs", "口腔医学硕士"),
	GgwsSs("GgwsSs", "公共卫生硕士"),
	HlSs("HlSs", "护理硕士"),
	YaoxSs("YaoxSs", "药学硕士"),
	ZyxSs("ZyxSs", "中药学硕士"),
	JsSs("JsSs", "军事硕士"),
	GsglSs("GsglSs", "工商管理硕士"),
	GgglSs("GgglSs", "公共管理硕士"),
	KjSs("KjSs", "会计硕士"),
	TsqbSs("TsqbSs", "图书情报硕士"),
	GcglSs("GcglSs", "工程管理硕士"),
	YsSs("YsSs", "艺术硕士"),
	ZxXs("ZxXs", "哲学学士"),
	JjxXs("JjxXs", "经济学学士"),
	FxXs("FxXs", "法学学士"),
	JyxXs("JyxXs", "教育学学士"),
	WxXs("WxXs", "文学学士"),
	LsxXs("LsxXs", "历史学学士"),
	LxXs("LxXs", "理学学士"),
	GxXs("GxXs", "工学学士"),
	NxXs("NxXs", "农学学士"),
	YxXs("YxXs", "医学学士"),
	JsxXs("JsxXs", "军事学学士"),
	GlxXs("GlxXs", "管理学学士"),
	YsxXs("YsxXs", "艺术学学士"),
	JzxXs("JzxXs", "建筑学学士"),
	Jw("Jw", "境外"),
	No("No", "无")
	;

	private final String id;
	private final String name;

	UserDegreeEnum(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, UserDegreeEnum.class).getName();
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
		UserDegreeEnum[] values = UserDegreeEnum.values();
		for (UserDegreeEnum item : values) {
			if (item.getName().equals(name)) {
				return item.getId();
			}
		}
		return null;
	}
}
