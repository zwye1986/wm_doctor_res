package com.pinde.sci.enums.gzykdx;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

/**
 * @author littlesheep
 * @Copyright njpdxx.com
 * <p/>
 * @since 2017/3/10
 */
public enum GzykdxDegreeTypeEnum implements GeneralEnum<String> {
    AcademicType("Academic","学术型"),
    ProfessionalType ("Profession","专业型");
    private final String id;
    private final String name;

    GzykdxDegreeTypeEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, GzykdxDegreeTypeEnum.class).getName();
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
