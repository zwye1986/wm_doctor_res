package com.pinde.sci.enums.osca;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ScoreStatusEnum implements GeneralEnum<String> {
    Save("Save","保存"),
    NotSubmit("NotSubmit","未提交"),
    Submit("Submit","已提交");
    private final String id;
    private final String name;

    ScoreStatusEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, ScoreStatusEnum.class).getName();
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
