package com.pinde.core.common.enums;

import com.pinde.core.util.EnumUtil;

/**
 * 跟师学习表单状态
 */
public enum DeptActivityStatusEnum implements GeneralEnum<String> {

    Save("Save","保存"),
    Submit("Submit","已提交"),
    Pass("Pass","审核通过"),
    UnPass("UnPass","审核不通过")
    ;
    private final String id;
    private final String name;

    DeptActivityStatusEnum(String id, String name) {
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
        return EnumUtil.getById(id, DeptActivityStatusEnum.class).getName();
    }
}
