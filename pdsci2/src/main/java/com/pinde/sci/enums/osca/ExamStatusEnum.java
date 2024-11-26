package com.pinde.sci.enums.osca;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ExamStatusEnum implements GeneralEnum<String> {
    Waiting("Waiting","排队"),
    StayAssessment("StayAssessment","待考核"),
    Assessment("Assessment","已考核"),
    AssessIng("AssessIng","考核中");
    private final String id;
    private final String name;

    ExamStatusEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, ExamStatusEnum.class).getName();
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
