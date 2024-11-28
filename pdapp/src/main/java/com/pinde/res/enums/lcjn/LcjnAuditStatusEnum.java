package com.pinde.res.enums.lcjn;


import com.pinde.core.common.GeneralEnum;
import com.pinde.core.util.EnumUtil;

/**
 * 学员预约状态
 */
public enum LcjnAuditStatusEnum implements GeneralEnum<String> {
    Passed("Passed","通过"),
    UnPassed("UnPassed","不通过"),
    Invalid("Invalid","失效"),
    Passing("Passing","待审核");
    private final String id;
    private final String name;

    LcjnAuditStatusEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, LcjnAuditStatusEnum.class).getName();
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
