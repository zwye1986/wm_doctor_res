package com.pinde.core.common.enums;

import com.pinde.core.common.GeneralEnum;
import com.pinde.core.util.EnumUtil;

/**
 * 跟师学习表单状态
 */
public enum DeptActivityTypeEnum implements GeneralEnum<String> {
    Dept("Dept","科室"),
    Scientific("Scientific","教研室")
    ;
    private final String id;
    private final String name;

    DeptActivityTypeEnum(String id, String name) {
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
        return EnumUtil.getById(id, DeptActivityTypeEnum.class).getName();
    }
}
