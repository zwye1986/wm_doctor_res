package com.pinde.sci.enums.sczyres;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum SczyBaseStatusEnum implements GeneralEnum<String> {

	NotSubmit("NotSubmit","未提交"),
	Auditing("Auditing","待审核"),
	LocalPassed("LocalPassed","基地审核通过"),
	LocalUnPassed("LocalUnPassed","基地审核不通过"),
	XtLocalPassed("XtLocalPassed" , "协同基地审核通过"),
	XtLocalUnPassed("XtLocalUnPassed" , "协同基地审核不通过"),
	GlobalPassed("GlobalPassed" , "省厅审核通过"),
	GlobalUnPassed("GlobalUnPassed" , "省厅审核不通过"),
	Passed("Passed" , "审核通过"),
	NotPassed("NotPassed" , "审核不通过")
	;

	private final String id;
	private final String name;

	SczyBaseStatusEnum(String id, String name) {
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
		return EnumUtil.getById(id, SczyBaseStatusEnum.class).getName();
	}
}
