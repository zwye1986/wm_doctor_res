package com.pinde.sci.enums.fstu;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ProStatusEnum implements GeneralEnum<String> {
    Draft("Draft","未送审"),
    Apply("Apply","待审核"),
    Passed("Passed", "审核通过"),
    UnPassed("UnPassed", "退回");
    private final String id;
    private final String name;

    ProStatusEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, ProStatusEnum.class).getName();
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
