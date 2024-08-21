package com.pinde.sci.enums.njmuedu;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum NjmuEduQuestionStatusEnum implements GeneralEnum<String> {
	
	Answered("Answered","已回答"),
	Unanswered("Unanswered","未回答")
	;

	private final String id;
	private final String name;
	
	NjmuEduQuestionStatusEnum(String id,String name) {
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
		return EnumUtil.getById(id, NjmuEduQuestionStatusEnum.class).getName();
	}
}
