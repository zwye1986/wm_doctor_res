package com.pinde.sci.enums.nyzl;


import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum NyzlStuSignEnum implements GeneralEnum<String> {

    DoctoralStudent("DoctoralStudent","博士生招录管理"),
    MasterStudent("MasterStudent","硕士生招录管理"),
    SummerCamp("SummerCamp","夏令营招录管理"),
    PushFreeStudent("PushFreeStudent","推免生招录管理")
    ;
    private final String id;
    private final String name;

    NyzlStuSignEnum(String id,String name) {
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
        return EnumUtil.getById(id, NyzlStuSignEnum.class).getName();
    }
}
