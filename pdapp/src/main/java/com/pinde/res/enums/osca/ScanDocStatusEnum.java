package com.pinde.res.enums.osca;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ScanDocStatusEnum implements GeneralEnum<String> {
    StayAssessment("StayAssessment","待考核"),
    NotSubmit("NotSubmit","未提交"),
    Submit("Submit","已提交");
    private final String id;
    private final String name;

    ScanDocStatusEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, ScanDocStatusEnum.class).getName();
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
