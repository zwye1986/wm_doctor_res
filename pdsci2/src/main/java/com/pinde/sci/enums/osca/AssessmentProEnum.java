package com.pinde.sci.enums.osca;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum AssessmentProEnum implements GeneralEnum<String> {
    ProvincialPlan("ProvincialPlan","省级方案"),
    HospitalPlan("HospitalPlan","院级方案");
    private final String id;
    private final String name;

    AssessmentProEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, AssessmentProEnum.class).getName();
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
