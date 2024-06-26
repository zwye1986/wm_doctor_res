package com.pinde.sci.enums.zseyjxres;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum UnitPropertyEnum implements GeneralEnum<String> {
    StateOwned("StateOwned","国有"),
    Collective("Collective","集体"),
    PrivateEconomy("PrivateEconomy","私营经济"),
    IndividualEconomy("IndividualEconomy","个体经济"),
    JointVentureEconomy("JointVentureEconomy","联营经济"),
    ShareholdingSystem("ShareholdingSystem","股份制"),
    ForeignInvestment("ForeignInvestment","外商投资"),
    HKAndMacaoAndTaiwan("HKAndMacaoAndTaiwan","港澳台投资"),
    Other("Other","其他");

    private final String id;
    private final String name;

    UnitPropertyEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, UnitPropertyEnum.class).getName();
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
