package com.pinde.res.enums.jszy;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

/**
 * 跟师学习表单状态
 */
public enum NoteTypeEnum implements GeneralEnum<String> {
    FollowTeacherRecord("FollowTeacherRecord","跟师记录"),
    Note("Note","学习笔记"),
    Experience("Experience","跟师心得"),
    BookRecord("BookRecord","医籍学习记录"),
    BookExperience("BookExperience","医籍学习体会"),
    TypicalCases("TypicalCases","经典医案"),
    AnnualAssessment("AnnualAssessment","年度考核表"),
    GraduationAssessment("GraduationAssessment","结业考核表"),
    ;
    private final String id;
    private final String name;

    NoteTypeEnum(String id, String name) {
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
        return EnumUtil.getById(id, NoteTypeEnum.class).getName();
    }
}
