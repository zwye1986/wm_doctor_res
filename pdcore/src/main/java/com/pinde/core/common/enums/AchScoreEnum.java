package com.pinde.core.common.enums;

import com.pinde.core.common.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum AchScoreEnum implements GeneralEnum<String> {
    Enable(com.pinde.core.common.GlobalConstant.FLAG_Y, "启用"),
    Disable(com.pinde.core.common.GlobalConstant.FLAG_N, "停用");

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
