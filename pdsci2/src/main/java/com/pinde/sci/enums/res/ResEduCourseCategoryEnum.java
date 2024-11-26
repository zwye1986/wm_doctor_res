package com.pinde.sci.enums.res;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ResEduCourseCategoryEnum implements GeneralEnum<String> {

    PrejobTrain("PrejobTrain", "岗前培训"),
    GeneraTrain("GeneraTrain", "普通培训");

    private final String id;
    private final String name;

    ResEduCourseCategoryEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, ResEduCourseCategoryEnum.class).getName();
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
