package com.pinde.sci.enums.jsres;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

/**
 * @ClassName CheckStatusEnum
 * @Deacription 审核状态
 * @Author shengl
 * @Date 2020-12-21 10:26
 * @Version 1.0
 **/
public enum CheckStatusEnum implements GeneralEnum<String> {

    Auditing("Auditing","待审核"),
    Passed("Passed" , "审核通过"),
    NotPassed("NotPassed" , "审核不通过")
    ;

    private final String id;
    private final String name;

    CheckStatusEnum(String id, String name) {
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
        return EnumUtil.getById(id, CheckStatusEnum.class).getName();
    }

}
