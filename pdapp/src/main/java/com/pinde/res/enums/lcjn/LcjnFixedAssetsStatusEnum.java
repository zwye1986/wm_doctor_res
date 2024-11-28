package com.pinde.res.enums.lcjn;


import com.pinde.core.common.GeneralEnum;
import com.pinde.core.util.EnumUtil;

/**
 * 固定资产状态
 */
public enum LcjnFixedAssetsStatusEnum implements GeneralEnum<String> {
    Normal("Normal","正常"),
    Damage("Damage","损坏"),
    Maintenance("Maintenance","维修");
    private final String id;
    private final String name;

    LcjnFixedAssetsStatusEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, LcjnFixedAssetsStatusEnum.class).getName();
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
