package com.pinde.sci.enums.recruit;


import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum RecruitStatusEnum implements GeneralEnum<String> {
    Edit("Save","保存"),
    Passing("Passing","待审核"),
    Passed("Passed","审核通过"),
    NoPassed("NoPassed","审核不通过");

    private RecruitStatusEnum(String id, String name) {
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
        return EnumUtil.getNameById(id, RecruitStatusEnum.class);
    }
}
