package com.pinde.core.common.enums;

import com.pinde.core.common.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum TechnicalPositionEnum implements GeneralEnum<String> {


	ChiefPhysician("chiefPhysician","主任医师"),
	AssociateChiefPhysician("associateChiefPhysician","副主任医师"),
	PhysicianInCharge("physicianInCharge","主治医师"),
	SeniorTechnologist("seniorTechnologist","主任技师"),
	DeputyChiefTechnician("deputyChiefTechnician","副主任技师"),
	AttendingTechnician("attendingTechnician","主管技师");

	private final String id;
	private final String name;

	TechnicalPositionEnum(String id, String name) {
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
		return EnumUtil.getById(id, TechnicalPositionEnum.class).getName();
	}

	public static String getIdByName(String name) {
		for (TechnicalPositionEnum value : TechnicalPositionEnum.values()) {
			if (value.getName().equals(name)) {
				return value.getId();
			}
		}
		return "";
	}
}
