package com.pinde.sci.enums.jsres;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum TrainCategoryEnum implements GeneralEnum<String> {
	
	WMFirst("WMFirst","一阶段","BeforeCfgDate"),
	WMSecond("WMSecond","二阶段","Independent"),
	DoctorTrainingSpe("DoctorTrainingSpe","住院医师","AfterCfgDate"),
	AssiGeneral("AssiGeneral","助理全科","Independent")
	;

	private final String id;
	private final String name;
	private final String typeId;
	
	TrainCategoryEnum(String id,String name,String typeId) {
		this.id = id;
		this.name = name;
		this.typeId = typeId;
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

	public static String getNameById(String id) {
		return EnumUtil.getById(id, TrainCategoryEnum.class).getName();
	}
	
	public static String getCategoryIdById(String id) {
		return ((TrainCategoryEnum)EnumUtil.getById(id, TrainCategoryEnum.class)).getTypeId();
	}
}
