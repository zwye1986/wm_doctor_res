package com.pinde.sci.enums.czyyjxres;


import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum GenderEnum implements GeneralEnum<String> {
    Man("Man", "男"),
    Woman("Woman", "女"),
    Male("Male","男"),
    FeMale("FeMale","女"),
    Other("Other","其他")
    ;

    private final String id;
    private final String name;

    GenderEnum(String id,String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, GenderEnum.class).getName();
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