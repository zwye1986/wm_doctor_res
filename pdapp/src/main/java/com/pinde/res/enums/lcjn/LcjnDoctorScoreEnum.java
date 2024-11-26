package com.pinde.res.enums.lcjn;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

/**
 * 成绩录入状态
 */
public enum LcjnDoctorScoreEnum implements GeneralEnum<String> {
    NotEntered("NotEntered","未录入"),
    HasBeenEntered("HasBeenEntered","已录入");

    private final String id;
    private final String name;

    LcjnDoctorScoreEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, LcjnDoctorScoreEnum.class).getName();
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
