package com.pinde.sci.enums.srm;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum AchScoreEnum implements GeneralEnum<String> {
    Enable("Y","启用"),
    Disable("N","停用");

    private final String id;
    private final String name;

    private AchScoreEnum(String id, String name) {
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
        return EnumUtil.getById(id, AchScoreEnum.class).getName();
    }

}
