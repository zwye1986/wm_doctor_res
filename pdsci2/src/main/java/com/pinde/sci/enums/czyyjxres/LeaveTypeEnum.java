package com.pinde.sci.enums.czyyjxres;


import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum LeaveTypeEnum implements GeneralEnum<String> {

    Leave("Leave","事假"),
    SickLeave("SickLeave","病假"),
    Holiday("Holiday","公假")
    ;
    private final String id;
    private final String name;


    LeaveTypeEnum(String id,String name) {
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
        return EnumUtil.getById(id, LeaveTypeEnum.class).getName();
    }

}
