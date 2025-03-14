package com.pinde.core.common.enums;

import com.pinde.core.common.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum CertificateLevelEnum implements GeneralEnum<String> {


	Country("1","国家级"),
	Province("2","省级"),
	City("3","市级"),
	Hospital("4","院级");

	private final String id;
	private final String name;

	CertificateLevelEnum(String id, String name) {
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
		return EnumUtil.getById(id, CertificateLevelEnum.class).getName();
	}

	public static String getIdByName(String name) {
		for (CertificateLevelEnum value : CertificateLevelEnum.values()) {
			if (value.getName().equals(name)) {
				return value.getId();
			}
		}
		return "";
	}
}
