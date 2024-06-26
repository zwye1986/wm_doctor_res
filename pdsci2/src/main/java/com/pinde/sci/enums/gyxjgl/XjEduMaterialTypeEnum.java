package com.pinde.sci.enums.gyxjgl;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum XjEduMaterialTypeEnum implements GeneralEnum<String> {

    Kcdg("Kcdg", "课程大纲"),
    Kcjdb("Kcjdb", "课程进度表");
    private final String id;
    private final String name;


    XjEduMaterialTypeEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, XjEduMaterialTypeEnum.class).getName();
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
