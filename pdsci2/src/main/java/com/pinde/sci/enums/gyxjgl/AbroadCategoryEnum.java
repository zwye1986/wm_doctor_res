package com.pinde.sci.enums.gyxjgl;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

/**
 * @Copyright njpdxx.com
 * @since 2018/4/9
 */
public enum AbroadCategoryEnum implements GeneralEnum<String> {

    Gjgplp("Gjgplp","国家公派联培"),
    Gjgpdb("Gjgpdb","国家公派读博"),
    Xjjllp("Xjjllp","校际交流联培"),
    Cgdqxx("Cgdqxx","出国短期学习"),
    Cjgjhy("Cjgjhy","参加国际会议"),
    Yscg("Yscg","因私出国"),
    Other("Other","其他");

    private final String id;
    private final String name;

    AbroadCategoryEnum(String id, String name) {
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
        return EnumUtil.getById(id, AbroadCategoryEnum.class).getName();
    }
}