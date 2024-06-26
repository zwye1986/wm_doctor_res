package com.pinde.sci.enums.xjgl;


import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum XjNyqjStatusEnum implements GeneralEnum<String> {

    Passing("Passing","待审核"),
    Passed("Passed","审核通过"),
    UnPassed("UnPassed","审核不通过")
//    ,NotNeedAudited("NotNeedAudited","不需审核")
    ;

    private final String id;
    private final String name;

    XjNyqjStatusEnum(String id,String name) {
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
        return EnumUtil.getById(id, XjNyqjStatusEnum.class).getName();
    }
}
