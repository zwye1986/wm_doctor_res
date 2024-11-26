package com.pinde.res.enums.osca;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum AuditStatusEnum implements GeneralEnum<String> {
    Passed("Passed","通过"),
    UnPassed("UnPassed","未通过"),
    Passing("Passing","待审核");
    private final String id;
    private final String name;

    AuditStatusEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, AuditStatusEnum.class).getName();
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
