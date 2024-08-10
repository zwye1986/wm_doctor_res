package com.pinde.sci.enums.jsres;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum JsresSendMessageEnum implements GeneralEnum<String> {
    SUCCESS("100","发送成功"),
    CHECKFAILED("101","验证失败"),
    SMSINSUFFICIENT("102","短信不足"),
    OPERATIONFAILED("103","操作失败"),
    ILLEGALCHARACTER("104","非法字符"),
    TOOMUCHCONTENT("105","内容过多"),
    TOOMUCHNUMBER("106","号码过多"),
    TOOFASTFREQUENCY("107","频率过快"),
    EMPTYNUMBER("108","号码内容空"),
    SUSPENDANACCOUNT("109","账号冻结"),
    ERRORNUMBER("112","号码错误"),
    TIMINGERROR("113","定时出错"),
    DISABLEINTERFACESEND("116","禁止接口发送"),
    IPBINDINCORRECT("117","绑定IP不正确"),
    NOSMSTEMPLATEADD("161","未添加短信模板"),
    TEMPLATEERROR("162","模板格式不正确"),
    TEMPLATIPEERROR("163","模板ID不正确"),
    TEXTTEMPLATENOTMATCH("164","全文模板不匹配"),
    TEMPLATEDUPLICATION("166","模板内容重复"),
    TEMPLATEUNDERREVIEW("167","模板审核中"),
    TEMPLATENOTAPPROVED("168","模板审核不通过");


    private final String id;
    private final String name;

    JsresSendMessageEnum(String id, String name) {
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
        return EnumUtil.getById(id, JsresSendMessageEnum.class).getName();
    }
}
