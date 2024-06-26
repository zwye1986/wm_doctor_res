package com.pinde.sci.enums.res;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

/**
 * 跟师学习表单状态
 */
public enum DeptActivityUserTypeEnum implements GeneralEnum<String> {
    Charge("Charge","负责人"),
    Invigilator("Invigilator","监考人"),
    GroupLeader("GroupLeader","组长"),
    Member("Member","考核成员")
    ;
    private final String id;
    private final String name;

    DeptActivityUserTypeEnum(String id, String name) {
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
        return EnumUtil.getById(id, DeptActivityUserTypeEnum.class).getName();
    }
}
