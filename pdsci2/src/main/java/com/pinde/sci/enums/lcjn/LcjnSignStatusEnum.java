package com.pinde.sci.enums.lcjn;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

/**
 * 签到状态
 */
public enum LcjnSignStatusEnum implements GeneralEnum<String> {
    SignIn("SignIn","已签到"),
    NoSignIn("NoSignIn","未签到");
    private final String id;
    private final String name;

    LcjnSignStatusEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, LcjnSignStatusEnum.class).getName();
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
