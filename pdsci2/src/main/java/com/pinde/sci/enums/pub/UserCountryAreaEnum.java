package com.pinde.sci.enums.pub;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;
import com.pinde.core.util.StringUtil;

public enum UserCountryAreaEnum implements GeneralEnum<String> {

	China("156", "中华人民共和国"),
	XiangGang("344", "中国香港特别行政区"),
	AoMen("446", "中国澳门特别行政区"),
	TaiWan("158", "中国台湾"),
	AFuHan("004", "阿富汗"),
	Asbj("031", "阿塞拜疆"),
	Mjlg("050", "孟加拉国"),
	JianPuSai("116", "柬埔寨"),
	KeMianLong("120", "喀麦隆"),
	NiBoEr("524", "尼泊尔"),
	YinDu("356", "印度"),
	Hskst("398", "哈萨克斯坦"),
	Wzbkst("860", "乌兹别克斯坦"),
	Jejst("417", "吉尔吉斯坦"),
	KenNiYa("404", "肯尼亚"),
	LaoGuo("418", "老挝"),
	Mlxy("458", "马来西亚"),
	Ydnxy("360", "印度尼西亚"),
	Mlqs("480", "毛里求斯"),
	MaLaWei("454", "马拉维"),
	Bjst("586", "巴基斯坦"),
	JiaNa("288", "加纳"),
	Aseby("231", "埃塞俄比亚"),
	ZanBiYa("894", "赞比亚"),
	Jbbw("716", "津巴布韦"),
	YaMaiJia("388", "牙买加"),
	YueDan("400", "约旦"),
	Tsny("834", "坦桑尼亚"),
	TaiGuo("764", "泰国"),
	MianDian("104", "缅甸"),
	LuWangda("646", "卢旺达"),
	Blst("275", "巴勒斯坦"),
	Nrly("566", "尼日利亚"),
	Sllk("144", "斯里兰卡"),
	Snje("686", "塞内加尔"),
	Slly("694", "塞拉利昂"),
	SuoMaLi("706", "索马里"),
	YueNan("704", "越南"),
	Tjkst("762", "塔吉克斯坦"),
	Other("other", "其他国家")
	;

	private final String id;
	private final String name;

	UserCountryAreaEnum(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, UserCountryAreaEnum.class).getName();
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
		UserCountryAreaEnum[] values = UserCountryAreaEnum.values();
		for (UserCountryAreaEnum item : values) {
			if (item.getName().equals(name)) {
				return item.getId();
			}
		}
		return null;
	}
}
