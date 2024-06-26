package com.pinde.sci.enums.zsey;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ZseyAuditStatusEnum implements GeneralEnum<String> {
    Passed("Passed","审核通过"),
    Passing("Passing","待审核");
    private final String id;
    private final String name;

    ZseyAuditStatusEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, ZseyAuditStatusEnum.class).getName();
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
