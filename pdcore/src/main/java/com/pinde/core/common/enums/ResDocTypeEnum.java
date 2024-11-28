package com.pinde.core.common.enums;

import com.pinde.core.common.GeneralEnum;
import com.pinde.core.util.EnumUtil;

import java.util.HashMap;
import java.util.Map;

public enum ResDocTypeEnum implements GeneralEnum<String> {

	Company("Company","本单位人"),
    CompanyEntrust("CompanyEntrust", "委培单位人"),
    //	Social("Social","行业人"),
	Social("Social","社会人"),
    Graduate("Graduate", "在校专硕"),
	;
	private final String id;
	private final String name;


    ResDocTypeEnum(String id, String name) {
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
        return EnumUtil.getById(id, com.pinde.core.common.enums.ResDocTypeEnum.class).getName();
    }

    public static final Map<String, String> map = new HashMap();

    static {
        for (ResDocTypeEnum tEnum : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
            map.put(tEnum.getId(), tEnum.getName());
        }
	}
}
