package com.pinde.sci.enums.res;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ArmyTypeEnum
 * @Deacription 是否军队人员
 * @Author shengl
 * @Date 2020-07-21 09:20
 * @Version 1.0
 **/
public enum ArmyTypeEnum implements GeneralEnum<String> {

    NO("NO","否"),
    ActiveArmy("ActiveArmy","现役军人"),
    CivilPersonnel("CivilPersonnel","文职人员"),
    MilitaryAcademy("MilitaryAcademy","军队院校研究生")
    ;

    private final String id;
    private final String name;

    ArmyTypeEnum(String id,String name) {
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
        return EnumUtil.getById(id, ArmyTypeEnum.class).getName();
    }
    public static final Map<String, String> map = new HashMap();

    static {
        for (ArmyTypeEnum tEnum : ArmyTypeEnum.values()) {
            map.put(tEnum.getId(), tEnum.getName());
        }
    }
}
