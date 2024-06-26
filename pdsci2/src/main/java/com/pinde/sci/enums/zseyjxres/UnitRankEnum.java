package com.pinde.sci.enums.zseyjxres;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum UnitRankEnum implements GeneralEnum<String> {
    ThreeTop("threeTop","三级特等"),
    ThreeA("threeA","三级甲等"),
    ThreeB("ThreeB","三级乙等"),
    ThreeC("ThreeC","三级丙等"),
    TwoA("TwoA","二级甲等"),
    TwoB("TwoB","二级乙等"),
    TwoC("TwoC","二级丙等"),
    OneA("OneA","一级甲等"),
    OneB("OneB","一级乙等"),
    OneC("OneC","一级丙等");


    private final String id;
    private final String name;

    UnitRankEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, UnitRankEnum.class).getName();
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
