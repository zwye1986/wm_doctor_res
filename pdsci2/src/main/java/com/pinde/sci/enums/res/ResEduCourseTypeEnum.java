package com.pinde.sci.enums.res;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ResEduCourseTypeEnum implements GeneralEnum<String> {

    Required("Required", "必修课"),
    Optional("Optional", "选修课"),
    Public("Public", "公开课");

    private final String id;
    private final String name;

    ResEduCourseTypeEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, ResEduCourseTypeEnum.class).getName();
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
