package com.pinde.sci.enums.res;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

/**
 * 评分类型Enum
 * @author Administrator
 */
public enum ResAssessTypeEnum implements GeneralEnum<String> {

	TeacherDoctorAssess360("TeacherDoctorAssess","临床专业指导医师评估住院医师量表"),
	TeacherDoctorAssessTwo360("TeacherDoctorAssessTwo","医技专业指导医师评估住院医师量表"),
	NurseDoctorAssess360("NurseDoctorAssess","临床专业护士评估住院医师"),
	DoctorTeacherAssess360("TeacherAssess","住院医师评临床专业指导医师估量表"),
	DoctorTeacherAssessTwo360("TeacherAssessTwo","住院医师评估医技专业指导医师量表"),
	DoctorHeadAssess360("DeptAssess","学员评价科室"),
	TeacherAssess("TeacherAssess","学员对带教老师评分"),
	DeptAssess("DeptAssess","学员对科室评分"),
	ManagerAssess("ManagerAssess","学员对继教科评分"),
	HeadDoctorAssess("HeadDoctorAssess","科室对学员评分"),
	TeacherDoctorAssess("TeacherDoctorAssess","带教老师对学员评分"),
	NurseDoctorAssess("NurseDoctorAssess","护士对学员评分"),
	ManageDoctorAssess360("ManageDoctorAssess360","管理老师对学员的评价"),
	PatientDoctorAssess("PatientDoctorAssess","患者对学员评分"),
	SecretaryDoctorAssess("SecretaryDoctorAssess","规培秘书对学员评分"),
	PatientDoctorAssess360("PatientDoctorAssess360","病人对学员评分");
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
