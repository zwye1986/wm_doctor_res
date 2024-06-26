package com.pinde.sci.enums.zseyjxres;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum CertificateTypesEn2Enum implements GeneralEnum<String> {

    Passport("Passport","Passport"),
    Taibaozheng("Taibaozheng","Hong Kong, Macao and Taiwan Identity Card");
	private final String id;
	private final String name;



	CertificateTypesEn2Enum(String id, String name) {
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
		return EnumUtil.getById(id, CertificateTypesEn2Enum.class).getName();
	}
}
