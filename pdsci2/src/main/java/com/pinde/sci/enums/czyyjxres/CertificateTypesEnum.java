package com.pinde.sci.enums.czyyjxres;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum CertificateTypesEnum implements GeneralEnum<String> {
	
	Shenfenzheng("Shenfenzheng","身份证(identity card)"),
	Junguanzheng("Junguanzheng","军官证(certificate of officers)"),
	Taibaozheng("Taibaozheng","港澳台身份证(Hong Kong, Macao and Taiwan identity card)"),
	Huaqiao("Huaqiao","华侨身份证(Overseas Chinese identity card)"),
	Passport("Passport","护照(passport)"),
	;
	private final String id;
	private final String name;
	


	CertificateTypesEnum(String id, String name) {
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
		return EnumUtil.getById(id, CertificateTypesEnum.class).getName();
	}
}
