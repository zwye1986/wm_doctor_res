package com.pinde.sci.enums.xjgl;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;
import com.pinde.core.util.StringUtil;

/**
 * @Copyright njpdxx.com
 * @since 2018/5/21
 */
public enum UserDegreeMajorEnum implements GeneralEnum<String> {

    Xljkjy("Xljkjy", "心理健康教育"),
    Swyxgc("Swyxgc", "生物医学工程"),
    Zygc("Zygc", "制药工程"),
    Nkx("Nkx", "内科学"),
    Ekx("Ekx", "儿科学"),
    Lnyx("Lnyx", "老年医学"),
    Sjbx("Sjbx", "神经病学"),
    Sjbyjswsx("Sjbyjswsx", "精神病与精神卫生学"),
    Pfbyxbx("Pfbyxbx", "皮肤病与性病学"),
    Yxyxyhyx("Yxyxyhyx", "影像医学与核医学"),
    Lcjyzdx("Lcjyzdx", "临床检验诊断学"),
    Wkx("Wkx", "外科学"),
    Fckx("Fckx", "妇产科学"),
    Ykx("Ykx", "眼科学"),
    Ebyhkx("Ebyhkx", "耳鼻咽喉科学"),
    Zlx("Zlx", "肿瘤学"),
    Kfyxyllx("Kfyxyllx", "康复医学与理疗学"),
    Ydyx("Ydyx", "运动医学"),
    Mzx("Mzx", "麻醉学"),
    Jzyx("Jzyx", "急诊医学"),
    Zynkx("Zynkx", "中医内科学"),
    Zywkx("Zywkx", "中医外科学"),
    Zygskx("Zygskx", "中医骨伤科学"),
    Zyfkx("Zyfkx", "中医妇科学"),
    Zyekx("Zyekx", "中医儿科学"),
    Zywgkx("Zywgkx", "中医五官科学"),
    Zjtnx("Zjtnx", "针灸推拿学"),
    Mzyx("Mzyx", "民族医学(含：藏医学、蒙医学等)"),
    Zxyjhlc("Zxyjhlc", "中西医结合临床"),
    Qkyx("Qkyx", "全科医学"),
    Bfly("Bfly", "不分领域"),
    Kqyx("Kqyx", "口腔医学（博士、硕士）"),
    GgwsSs("GgwsSs", "公共卫生（硕士）"),
    HlSs("HlSs", "护理（硕士）"),
    YxSs("YxSs", "药学（硕士）"),
    ZyxSs("ZyxSs", "中药学（硕士）")
    ;

    private final String id;
    private final String name;

    UserDegreeMajorEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, UserDegreeMajorEnum.class).getName();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public static String getIdByName(String name) {
        name = StringUtil.defaultString(name).trim();
        UserDegreeMajorEnum[] values = UserDegreeMajorEnum.values();
        for (UserDegreeMajorEnum item : values) {
            if (item.getName().equals(name)) {
                return item.getId();
            }
        }
        return null;
    }
}
