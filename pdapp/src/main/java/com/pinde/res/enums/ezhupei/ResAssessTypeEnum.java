package com.pinde.res.enums.ezhupei;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ResAssessTypeEnum implements GeneralEnum<String> {
	
	TeacherAssess("TeacherAssess","带教老师评分"),
	DeptAssess("DeptAssess","科室评分"),
	HeadDoctorAssess("HeadDoctorAssess","科室对学员评分"),
	TeacherDoctorAssess("TeacherDoctorAssess","带教老师对学员评分"),
	NurseDoctorAssess("NurseDoctorAssess","护士对学员评分"),
	PatientDoctorAssess("PatientDoctorAssess","患者对学员评分"),
	SecretaryDoctorAssess("SecretaryDoctorAssess","规培秘书对学员评分")
	;

	private final String id;
	private final String name;
	


	ResAssessTypeEnum(String id,String name) {
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
		return EnumUtil.getById(id, ResAssessTypeEnum.class).getName();
	}
}
