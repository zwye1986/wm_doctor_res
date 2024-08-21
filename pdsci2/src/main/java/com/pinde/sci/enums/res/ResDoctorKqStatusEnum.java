package com.pinde.sci.enums.res;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ResDoctorKqStatusEnum implements GeneralEnum<String> {

	Auditing("Auditing","待带教审核"),
	TeacherUnPass("TeacherUnPass","带教审核不通过"),
	TeacherPass("TeacherPass","带教审核通过"),
	HeadAuditing("HeadAuditing","待科主任审核"),
	HeadUnPass("HeadUnPass","主任审核不通过"),
	HeadPass("HeadPass","主任审核通过"),
	TutorUnPass("TutorUnPass","导师审核不通过"),
	TutorPass("TutorPass","导师审核通过"),
	ManagerAuditing("ManagerAuditing","待管理员审核"),
	ManagerUnPass("ManagerUnPass","请假审核不通过"),
	ManagerPass("ManagerPass","请假审核通过"),
	Revoke("Revoke","已撤销"),
	RevokeAuditing("RevokeAuditing","销假待带教审核"),
	RevokeTeacherPass("RevokeTeacherPass","销假带教审核通过"),
	RevokeTeacherUnPass("RevokeTeacherUnPass","销假带教审核不通过"),
	RevokeHeadAuditing("RevokeHeadAuditing","销假待科主任审核"),
	RevokeHeadPass("RevokeHeadPass","销假科主任审核通过"),
	RevokeHeadUnPass("RevokeHeadUnPass","销假科主任审核不通过"),
	RevokeManagerAuditing("RevokeManagerAuditing","销假待管理员审核"),
	RevokeManagerPass("RevokeManagerPass","销假审核通过"),
	RevokeManagerUnPass("RevokeManagerUnPass","销假审核不通过"),
	BackLeave("BackLeave","已撤销")
	;

	private final String id;
	private final String name;



	ResDoctorKqStatusEnum(String id, String name) {
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
		return EnumUtil.getById(id, ResDoctorKqStatusEnum.class).getName();
	}
}
