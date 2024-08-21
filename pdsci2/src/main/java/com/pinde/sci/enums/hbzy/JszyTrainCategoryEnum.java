package com.pinde.sci.enums.hbzy;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum JszyTrainCategoryEnum implements GeneralEnum<String> {
	ChineseMedicine("ChineseMedicine","中医","Independent","Y"),
	TCMGeneral("TCMGeneral","中医全科","Independent","Y"),
	TCMAssiGeneral("TCMAssiGeneral","中医助理全科","Independent","N")
	;

	private final String id;
	private final String name;
	private final String typeId;
	private final String isView;

	JszyTrainCategoryEnum(String id, String name, String typeId, String isView) {
		this.id = id;
		this.name = name;
		this.typeId = typeId;
		this.isView = isView;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	public String getTypeId() {
		return typeId;
	}
	public String getIsView() {
		return isView;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, JszyTrainCategoryEnum.class).getName();
	}
	
	public static String getCategoryIdById(String id) {
		return ((JszyTrainCategoryEnum)EnumUtil.getById(id, JszyTrainCategoryEnum.class)).getTypeId();
	}
}
