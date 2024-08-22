package com.pinde.sci.enums.njmuedu;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

/**
 * Created by yex on 2018-12-24.
 */

public enum NjmuEduAuditStatusEnum implements GeneralEnum<String> {
    Passed("Passed","医院审核通过"),
    UnPassed("UnPassed","医院审核不通过"),
    PassedTwo("PassedTwo","通过"),
    UnPassedTwo("UnPassedTwo","未通过"),
    Passing("Passing","待医院审核");
    private final String id;
    private final String name;

    NjmuEduAuditStatusEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, NjmuEduAuditStatusEnum.class).getName();
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
