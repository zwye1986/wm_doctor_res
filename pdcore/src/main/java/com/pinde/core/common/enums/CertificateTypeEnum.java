package com.pinde.core.common.enums;

import com.pinde.core.common.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum CertificateTypeEnum implements GeneralEnum<String> {

    Shenfenzheng("01", "居民身份证"),
    //	Junguanzheng("02","军官证"),
	/*Taibaozheng("03","港澳台身份证"),
	Huaqiao("04","华侨身份证"),*/
    Passport("05", "外籍护照"),
    HongKongMacao("06", "港澳居民来往内地通行证"),
    Formosa("07", "台湾居民来往大陆通行证"),
    TaiWanJuZhu("08", "台湾居民居住证"),
    Foreign("09", "外国人永久居留证"),
    HongKongJuZhu("10", "港澳居民居住证"),
	;
	private final String id;
	private final String name;


    CertificateTypeEnum(String id, String name) {
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
		return EnumUtil.getById(id, CertificateTypeEnum.class).getName();
	}
}
