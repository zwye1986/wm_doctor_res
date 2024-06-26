package com.pinde.sci.enums.res;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum XjPartStatusEnum implements GeneralEnum<String> {

	BaseInfo("BaseInfo","基本信息"),
	RecruitInfo("RecruitInfo","录取信息"),
	NeedInfo("NeedInfo","必填信息"),
	SelectInfo("SelectInfo","选填信息"),
	FeeInfo("FeeInfo","学费信息"),
	GotCertInfo("GotCertInfo","已获得学历或学位信息"),
	CertReqInfo("CertReqInfo","攻读学历学位信息"),
	PaperInfo("PaperInfo","学位论文信息"),
	WorkInfo("WorkInfo","就业信息"),
	//广州学籍
	DispatchInfo("DispatchInfo","派遣信息"),
	EmployInfo("EmployInfo","就业信息"),
	ArchivesInfo("ArchivesInfo","档案去向信息"),
	MedicalInfo("MedicalInfo","医保、孕育信息"),
	DormitoryInfo("DormitoryInfo","宿舍信息"),
	DossierInfo("DossierInfo","档案信息")
	;

	private final String id;
	private final String name;



	XjPartStatusEnum(String id, String name) {
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
		return EnumUtil.getById(id, XjPartStatusEnum.class).getName();
	}
}
