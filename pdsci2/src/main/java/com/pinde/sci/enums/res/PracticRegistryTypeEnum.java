package com.pinde.sci.enums.res;

import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

/**
 * Created by pdkj on 2018/1/3.
 */
public enum PracticRegistryTypeEnum implements GeneralEnum<String> {
    PatientsAndDisease("PatientsAndDisease","病人病种","Y","N","N"),
    HomeVisits("HomeVisits", "家庭随访","Y","N","N"),
    HypertensionMonitoring("HypertensionMonitoring","高血压监测","Y","N","N"),
    Type2DiabetesMonitoring("Type2DiabetesMonitoring","2型糖尿病监测","Y","N","N"),
    ChildHealthCare("ChildHealthCare","儿童保健","Y","N","N"),
    ElderHealthCare("ElderHealthCare","老年保健","Y","N","N"),
    WomenHealthCare("WomenHealthCare","妇女保健","Y","N","N"),
    DisableHealthCare("DisableHealthCare","残疾人保健","Y","N","N"),
    ImportantHealthCare("ImportantHealthCare","重点人群保健","Y","N","N"),
    CommunityLearning("CommunityLearning","社区学习","Y","N","N"),
    SocialPractice("SocialPractice","社区实践","Y","N","N"),
    Glmzbrhsjbzdj("Glmzbrhsjbzdj","管理门诊病人和所见病种登记","Y","N","N"),
    Cjjfsqdcjkjy("Cjjfsqdcjkjy","参加家访社区调查健康教育","Y","N","N"),
    Cjjnczdj("Cjjnczdj","参加技能操作登记","Y","N","N"),
    Cjkyjl("Cjkyjl","参加科研记录","Y","N","N"),
    Fblwzsgabdzw("Fblwzsgabdzw","发表论文综述个案报道泽文","Y","N","N"),
    Hjqk("Hjqk","获奖情况","Y","N","N"),
    Qtdj("Qtdj","其他登记","Y","N","N"),
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
    PracticRegistryTypeEnum(String id, String name, String haveReq, String haveAppeal, String haveItem) {
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
