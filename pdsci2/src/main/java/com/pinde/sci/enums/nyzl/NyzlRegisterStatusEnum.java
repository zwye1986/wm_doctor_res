package com.pinde.sci.enums.nyzl;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum  NyzlRegisterStatusEnum implements GeneralEnum<String> {

    Report("Report","报到"),
    NotReport("NotReport","未报到")
            ;
    private final String id;
    private final String name;

    NyzlRegisterStatusEnum(String id,String name) {
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
        return EnumUtil.getById(id, NyzlRegisterStatusEnum.class).getName();
    }
}