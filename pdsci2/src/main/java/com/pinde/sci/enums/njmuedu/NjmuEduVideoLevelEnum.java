package com.pinde.sci.enums.njmuedu;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum NjmuEduVideoLevelEnum implements GeneralEnum<String>{
	
	HyperCrystal("HyperCrystal","超清"),
	High("High","高清"),
	Standard("Standard","标清"),
	Topspeed("Topspeed","极速")
	;
	
	
	private final String id;
	private final String name;
	
	NjmuEduVideoLevelEnum(String id,String name){
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
		return EnumUtil.getById(id, NjmuEduVideoLevelEnum.class).getName();
	}
	
}
