package com.pinde.sci.enums.zseyjxres;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum UnitLevelEnum implements GeneralEnum<String> {
    MedicalSchool("MedicalSchool","医学院校"),
    Provincial("Provincial","省级"),
    Municipal("Municipal","市级"),
    County("County","县级"),
    District("District","区级"),
    Hospital("Hospital","卫生院"),
    HealthSchool("HealthSchool","卫生学校"),
    Force("Force","部队"),
    EnterpriseWorkers("EnterpriseWorkers","企业职工医院"),
    ForeignOrHKAndMacao("ForeignOrHKAndMacao","外资或港澳"),
    Other("Other","其他");

    private final String id;
    private final String name;

    UnitLevelEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, UnitLevelEnum.class).getName();
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
