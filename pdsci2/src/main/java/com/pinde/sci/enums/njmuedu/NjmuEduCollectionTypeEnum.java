package com.pinde.sci.enums.njmuedu;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum NjmuEduCollectionTypeEnum implements GeneralEnum<String> {
	
	Course("Course","课程"),
	Chapter("Chapter","章节")
	;

	private final String id;
	private final String name;
	
	NjmuEduCollectionTypeEnum(String id,String name) {
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
		return EnumUtil.getById(id, NjmuEduCollectionTypeEnum.class).getName();
	}
}
