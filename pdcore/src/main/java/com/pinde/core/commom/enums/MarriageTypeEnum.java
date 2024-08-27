package com.pinde.core.commom.enums;

import com.pinde.core.util.EnumUtil;

import java.util.ArrayList;
import java.util.List;

public enum MarriageTypeEnum implements GeneralEnum<String>{

	NotMarried("1","未婚"),
	Married("2","已婚"),
	Partner("3","丧偶"),
	Divorce("4","离婚"),
	Other("9","其他"),
	;
	private final String id;
	private final String name;
	


	MarriageTypeEnum(String id, String name) {
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
		List<String> enumIds=new ArrayList<String>();
		for(MarriageTypeEnum e:MarriageTypeEnum.values()){
			enumIds.add(e.getId());
		}
		if(enumIds.contains(id)){
			return EnumUtil.getById(id, MarriageTypeEnum.class).getName();
		}else{
			return "";
		}
		
	}
}
