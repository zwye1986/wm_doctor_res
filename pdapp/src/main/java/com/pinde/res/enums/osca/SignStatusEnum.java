package com.pinde.res.enums.osca;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum SignStatusEnum implements GeneralEnum<String> {
    SignIn("SignIn","已签到"),
    NoSignIn("NoSignIn","未签到");
    private final String id;
    private final String name;

    SignStatusEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, SignStatusEnum.class).getName();
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
