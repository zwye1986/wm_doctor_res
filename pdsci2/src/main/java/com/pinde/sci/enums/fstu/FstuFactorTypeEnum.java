package com.pinde.sci.enums.fstu;


import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum FstuFactorTypeEnum implements GeneralEnum<String> {
    inland("inland","SCI影响因子")
    ;

    private final String id;
    private final String name;


    private FstuFactorTypeEnum(String id, String name) {
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
        return EnumUtil.getById(id, FstuFactorTypeEnum.class).getName();
    }

}
