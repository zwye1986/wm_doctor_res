package com.pinde.res.enums.stdp;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ResultEnum implements GeneralEnum<String> {
	Success("200","Request Successful"),
	
	EmptyUserCode("30101","用户名为空"),
	EmptyPasswd("30102","密码为空"),
	ErrorUser("30199","用户名或密码错误"), 
	;

	private final String id;
	private final String name;
	
	ResultEnum(String id,String name) {
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
		return EnumUtil.getById(id, ResultEnum.class).getName();
	}
}
