package com.pinde.res.enums.stdp;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

/**
 * Created by pdkj on 2018/1/3.
 */
public enum TheoreticalRegistryTypeEnum implements GeneralEnum<String> {
    TheoreticalStudy("TheoreticalStudy","理论学习","Y","N","N"),
    ;
    private final String id;
    private final String name;
    private final String haveReq;
    private final String haveAppeal;
    private final String haveItem;

    /**
     *
     * @param id
     * @param name
     * @param haveReq 要求
     * @param haveAppeal 申述
     * @param haveItem 是否有子项
     */
    TheoreticalRegistryTypeEnum(String id, String name, String haveReq, String haveAppeal, String haveItem) {
        this.id = id;
        this.name = name;
        this.haveReq = haveReq;
        this.haveAppeal = haveAppeal;
        this.haveItem = haveItem;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getHaveReq() {
        return haveReq;
    }

    public String getHaveAppeal() {
        return haveAppeal;
    }

    public String getHaveItem() {
        return haveItem;
    }
    public static String getNameById(String id) {
        return EnumUtil.getById(id, ResRecTypeEnum.class).getName();
    }
}
