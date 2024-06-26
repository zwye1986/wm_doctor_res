package com.pinde.sci.enums.xjgl;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;


public enum XjNyqjApplyTypeEnum implements GeneralEnum<String> {

    SickLeave("SickLeave","病假"),
    PrivateLeave("PrivateLeave","事假")
    ;

    private final String id;
    private final String name;

    XjNyqjApplyTypeEnum(String id,String name) {
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
        return EnumUtil.getById(id, XjNyqjApplyTypeEnum.class).getName();
    }
}
