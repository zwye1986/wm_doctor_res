package com.pinde.res.enums.jswjw;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

import java.util.HashMap;
import java.util.Map;

public enum ProvinceEnum implements GeneralEnum<String> {

	Beijing("1","北京"),
	Tianjin("2","天津"),
	HeBei("3","河北"),
	ShanXi("4","山西"),
	NeiMengGu("5","内蒙古"),
	LiaoNing("6","辽宁"),
	JiLin("7","吉林"),
	HeiLongJiang("8","黑龙江"),
	ShangHai("9","上海"),
	JiangSu("10","江苏"),
	ZheJiang("11","浙江"),
	AnHui("12","安徽"),
	FuJian("13","福建"),
	JiangXi("14","江西"),
	ShanDong("15","山东"),
	HeNan("16","河南"),
	HuBei("17","湖北"),
	HuNan("18","湖南"),
	GuangDong("19","广东"),
	GuangXi("20","广西"),
	HaiNan("21","海南"),
	ChongQing("22","重庆"),
	SiChun("23","四川"),
	GuiZhou("24","贵州"),
	YunNan("25","云南"),
	XiZang("26","西藏"),
	ShanXi2("27","陕西"),
	GanSu("28","甘肃"),
	QingHai("29","青海"),
	NingXia("30","宁夏"),
	XinJiang("31","新疆"),
	BingTuan("32","兵团"),
	HongKang("33","香港"),
	AoMen("34","澳门"),
	TaiWan("35","台湾");
	private final String id;
	private final String name;



	ProvinceEnum(String id, String name) {
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
		return EnumUtil.getById(id, ProvinceEnum.class).getName();
	}

	public static final Map<String, String> map = new HashMap();

	static {
		for (ProvinceEnum tEnum : ProvinceEnum.values()) {
			map.put(tEnum.getId(), tEnum.getName());
		}
	}
}
