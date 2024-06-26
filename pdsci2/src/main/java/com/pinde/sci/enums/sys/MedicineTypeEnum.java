package com.pinde.sci.enums.sys;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum MedicineTypeEnum implements GeneralEnum<String> {
	ChineseMedicine("ChineseMedicine", "中医"),
	WesternMedicine("WesternMedicine", "西医"),
	ChineseMedicineWesternMedicine("ChineseMedicineWesternMedicine", "中西医")
	;
	private final String id;
	private final String name;



	MedicineTypeEnum(String id, String name) {
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
		if (EnumUtil.getById(id, MedicineTypeEnum.class) != null) {
			return EnumUtil.getById(id, MedicineTypeEnum.class).getName();
		} else {
			return "";
		}
	}
}
