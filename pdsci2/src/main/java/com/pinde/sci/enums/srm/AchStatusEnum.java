package com.pinde.sci.enums.srm;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum AchStatusEnum implements GeneralEnum<String>{
	Apply("Apply","填写"),
	Submit("Submit","待审核"),
	Save("Save","保存"),//管理员保存
	FirstAudit("FirstAudit","承担单位审核通过"),
	FirstBack("FirstBack","承担单位退回"),
	SecondAudit("SecondAudit","财务审核通过"),
	SecondBack("SecondBack","财务退回"),
    ThirdAudit("ThirdAudit", "科教科审核通过"),
    ThirdBack("ThirdBack", "科教科退回"),
    FourthAudit("FourthAudit", "科教科科长审核通过"),
    FourthBack("FourthBack", "科教科科长退回"),
    FifthAudit("FifthAudit","分管院长审核通过"),
    FifthBack("FifthBack","分管院长退回"),
	RollBack("RollBack","审核不通过"),
    LocalEdit("LocalEdit","承担单位修改"),
    Pass("Pass","审核通过")
	;
	private final String id;
	private final String name;
	
	private AchStatusEnum(String id, String name) {
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
		return EnumUtil.getById(id, AchStatusEnum.class).getName();
	}
}
