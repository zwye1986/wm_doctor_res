package com.pinde.core.common.enums;

import com.pinde.core.common.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum CertificateStatusEnum implements GeneralEnum<String> {

	Submit("Submit","提交"),
	ManagerPassed("ManagerPassed","基地审核通过"),
	GrantCertf("GrantCertf","同意发证"),
	UnGrantCertf("UnGrantCertf","暂不发证")
	;

	private final String id;
	private final String name;

	CertificateStatusEnum(String id, String name) {
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
		return EnumUtil.getById(id, CertificateStatusEnum.class).getName();
	}
}
