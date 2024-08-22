package com.pinde.sci.enums.edu;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum UserPageEnum implements GeneralEnum<String>{
	Student("xjgl/user/student", "学生信息页面"),
	Teacher("xjgl/user/teacher", "教师信息页面"),
	HospitalManager("xjgl/user/hospital", "医院管理员页面"),
	UniversityManager("xjgl/user/admin", "学校管理员页面"),
	AdminManager("xjgl/user/system", "系统管理员页面")
	;

	private String id;
	private String name;
	
	UserPageEnum(String id , String name){
		this.id = id;
		this.name = name;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, UserPageEnum.class).getName();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
}
