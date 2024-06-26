package com.pinde.sci.enums.fstu;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum RelTypeEnum implements GeneralEnum<String> {
    Lecture("Lecture", "论文"),
    Book("Book", "著作"),
    Award("Award", "报奖"),
    Achieve("Achieve", "成果"),
    Patent("Patent", "专利");
    private final String id;
    private final String name;

    RelTypeEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, RelTypeEnum.class).getName();
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
