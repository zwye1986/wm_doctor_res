package com.pinde.core.commom.enums;

import com.pinde.core.util.EnumUtil;

public enum CertificateTypeEnum implements GeneralEnum<String> {
	
	Shenfenzheng("01","身份证"),
	Junguanzheng("02","军官证"),
	Taibaozheng("03","港澳台身份证"),
	Huaqiao("04","华侨身份证"),
	Passport("05","护照"),
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
