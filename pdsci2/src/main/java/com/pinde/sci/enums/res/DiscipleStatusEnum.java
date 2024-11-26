package com.pinde.sci.enums.res;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

/**
 * 跟师学习表单状态
 */
public enum DiscipleStatusEnum implements GeneralEnum<String> {
    Apply("Apply","填写"),
    Submit("Submit","待审核"),
    DiscipleAudit("DiscipleAudit","待管理员审核"),
    DiscipleBack("DiscipleBack","师承老师审核不通过"),
    AdminAudit("AdminAudit","通过"),
    AdminBack("AdminBack","管理员审核不通过"),
    PendingAudit("PendingAudit","待审核"),
    Qualified("Qualified","通过"),
    UnQualified("UnQualified","不通过"),
    ;
    private final String id;
    private final String name;

    DiscipleStatusEnum(String id,String name) {
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
        return EnumUtil.getById(id, DiscipleStatusEnum.class).getName();
    }
}
