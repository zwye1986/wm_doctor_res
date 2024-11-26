package com.pinde.sci.enums.osca;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum DoctorScoreEnum implements GeneralEnum<String> {
    Passed("Passed","通过"),
    UnPassed("UnPassed","未通过"),
    PendingEnter("PendingEnter","待录入"),
    Miss("Miss","缺考");

    private final String id;
    private final String name;

    DoctorScoreEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, DoctorScoreEnum.class).getName();
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
