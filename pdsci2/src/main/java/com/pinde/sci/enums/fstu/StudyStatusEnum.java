package com.pinde.sci.enums.fstu;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum StudyStatusEnum implements GeneralEnum<String> {
    Apply("Apply", "填写"),
    Submit("Submit", "待审核"),
    FirstAudit("FirstAudit", "审核通过"),
    RollBack("RollBack", "审核不通过");
    private final String id;
    private final String name;

    StudyStatusEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, StudyStatusEnum.class).getName();
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
