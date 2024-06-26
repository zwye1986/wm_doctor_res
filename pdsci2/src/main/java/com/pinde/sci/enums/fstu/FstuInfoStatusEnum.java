package com.pinde.sci.enums.fstu;


import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum  FstuInfoStatusEnum implements GeneralEnum<String> {
    Edit("Edit","编辑"),
    Passing("Passing","待审核"),
    Passed("Passed","审核通过"),
    NoPassed("NoPassed","审核不通过"),
    Failure("Failure","删除")
            ;

    private FstuInfoStatusEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private final String id;
    private final String name;
    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }
    public static String getNameById(String id) {
        return EnumUtil.getNameById(id, FstuInfoStatusEnum.class);
    }
}
