package com.pinde.sci.enums.jsres;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum ConsultRoleEnum implements GeneralEnum<String> {

    Doctor("Doctor" , "学生"),
    Admin("Admin" , "基地")
    ;

    private final String id;
    private final String name;

    ConsultRoleEnum(String id,String name) {
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
        return EnumUtil.getById(id, BaseStatusEnum.class).getName();
    }
}
