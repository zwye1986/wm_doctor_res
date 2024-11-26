package com.pinde.sci.enums.sczyres;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum SczyRecDocTypeEnum implements GeneralEnum<String> {
	
	Industry("Industry","社会人"),
	Agency("Agency","本单位人"),
	Entrust("Entrust","委培单位人"),
	Graduate("Graduate","并轨专硕"),
	;
	private final String id;
	private final String name;
	


	SczyRecDocTypeEnum(String id,String name) {
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
		return EnumUtil.getById(id, SczyRecDocTypeEnum.class).getName();
	}
}
