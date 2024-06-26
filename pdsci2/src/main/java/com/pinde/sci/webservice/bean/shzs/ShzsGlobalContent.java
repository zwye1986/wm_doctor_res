package com.pinde.sci.webservice.bean.shzs;

import com.pinde.core.util.DateUtil;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.SysUser;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yex on 2018-11-14.
 */
public class ShzsGlobalContent {

    public static final String SHZS_ORG_FLOW = "f5a4634cbc8240478e5952d273d007b4";
    public static final String SHZS_ORG_NAME = "上海复旦大学附属中山医院";
    public static final String SHZS_WEBSERVICE_KEY = "F5A4634CBC8240478E5952D273D007B4";
    public static final String RES_WS_ID = "res";
    public static final String RECORD_STATUS_Y = "Y";
    public static final String RECORD_STATUS_N = "N";
    public static final String SHZS_HEAD_ROLE_FLOW = "79e410bd983b4b3496400be147e19122";
    public static final String SHZS_TEACHER_ROLE_FLOW = "3b41ad36c25b4ceebaaf4c0e220c8a71";
    public static final String SHZS_DOCTOR_ROLE_FLOW = "3cdf81b51e9443e692fb692205fa2210";
    public static List<String> doctorTypeIds=null;
    public static List<String> speIds=null;
    static {
        doctorTypeIds=new ArrayList<>();
        speIds=new ArrayList<>();
        doctorTypeIds.add("Company");
        doctorTypeIds.add("CompanyEntrust");
        doctorTypeIds.add("Social");
        doctorTypeIds.add("Graduate");

//        speIds.add("0100");
//        speIds.add("0200");
//        speIds.add("0300");
//        speIds.add("0400");
//        speIds.add("0500");
//        speIds.add("0600");
        speIds.add("0700");
//        speIds.add("0800");
//        speIds.add("0900");
//        speIds.add("1000");
//        speIds.add("1100");
//        speIds.add("1200");
//        speIds.add("1300");
//        speIds.add("1400");
//        speIds.add("1500");
//        speIds.add("1600");
//        speIds.add("1700");
//        speIds.add("1800");
//        speIds.add("1900");
//        speIds.add("2000");
//        speIds.add("2100");
//        speIds.add("2200");
//        speIds.add("2300");
//        speIds.add("2400");
//        speIds.add("2500");
//        speIds.add("2700");
//        speIds.add("2800");
//        speIds.add("2900");
//        speIds.add("3000");
//        speIds.add("3100");
//        speIds.add("3300");
//        speIds.add("3400");
//        speIds.add("3500");
    }
    //人员类型
    public enum DocTypeEnum  {
        Company("Company","单位人"),
        CompanyEntrust("CompanyEntrust", "委培人"),
        Social("Social","社会人"),
        Graduate("Graduate", "在校专硕");
        private final String id;
        private final String name;

        DocTypeEnum(String id, String name) {
            this.id = id;
            this.name = name;
        }
        public String getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public static String getNameById(String id) {
            if(id == null) return null;
            if(id instanceof String && StringUtils.isBlank((String)id)) return null;
            for (DocTypeEnum item : DocTypeEnum.values()) {
                if (item.getId().equals(id)) {
                    return item.getName();
                }
            }
            return null;
        }
    }
    //培训类型
    public enum DocTrainingTypeEnum  {
        Doctor("Doctor","住院医师")
        ;
        private final String id;
        private final String name;

        DocTrainingTypeEnum(String id, String name) {
            this.id = id;
            this.name = name;
        }
        public String getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public static String getNameById(String id) {
            if(id == null) return null;
            if(id instanceof String && StringUtils.isBlank((String)id)) return null;
            for (DocTrainingTypeEnum item : DocTrainingTypeEnum.values()) {
                if (item.getId().equals(id)) {
                    return item.getName();
                }
            }
            return null;
        }
    }
    //培训专业
    public enum SpeEnum  {
//        Spe0100("0100","内科"),
//        Spe0200("0200","儿科"),
//        Spe0300("0300","急诊科"),
//        Spe0400("0400","皮肤科"),
//        Spe0500("0500","精神科"),
//        Spe0600("0600","神经内科"),
        Spe0700("0700","全科医学科"),
//        Spe0800("0800","康复医学科"),
//        Spe0900("0900","外科"),
//        Spe1000("1000","外科（神外方向）"),
//        Spe1100("1100","外科（胸心外方向）"),
//        Spe1200("1200","外科（泌外方向）"),
//        Spe1300("1300","外科（整形方向）"),
//        Spe1400("1400","骨科"),
//        Spe1500("1500","外科（小儿外科）"),
//        Spe1600("1600","妇产科"),
//        Spe1700("1700","眼科"),
//        Spe1800("1800","耳鼻咽喉科"),
//        Spe1900("1900","麻醉科"),
//        Spe2000("2000","临床病理科"),
//        Spe2100("2100","检验医学科"),
//        Spe2200("2200","放射科"),
//        Spe2300("2300","超声医学科"),
//        Spe2400("2400","核医学科"),
//        Spe2500("2500","放射肿瘤科"),
//        Spe2700("2700","预防医学科"),
//        Spe2800("2800","口腔全科"),
//        Spe2900("2900","口腔内科"),
//        Spe3000("3000","口腔外科"),
//        Spe3100("3100","口腔修复科"),
//        Spe3300("3300","口腔病理科"),
//        Spe3400("3400","口腔颌面影像科"),
//        Spe3500("3500","西医助理全科")
        ;
        private final String id;
        private final String name;

        SpeEnum(String id, String name) {
            this.id = id;
            this.name = name;
        }
        public String getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public static String getNameById(String id) {
            if(id == null) return null;
            if(id instanceof String && StringUtils.isBlank((String)id)) return null;
            for (SpeEnum item : SpeEnum.values()) {
                if (item.getId().equals(id)) {
                    return item.getName();
                }
            }
            return null;
        }
    }
    public static void setRecordInfo(Object obj, boolean isAdd) {
        Class<?> clazz = obj.getClass();
        try {
            if (isAdd) {
                Method setRecordStatusMethod = clazz.getMethod("setRecordStatus", String.class);
                setRecordStatusMethod.invoke(obj, GlobalConstant.RECORD_STATUS_Y);
                Method setCreateTime = clazz.getMethod("setCreateTime", String.class);
                setCreateTime.invoke(obj, DateUtil.getCurrDateTime());
                Method setCreateUserFlow = clazz.getMethod("setCreateUserFlow", String.class);
                setCreateUserFlow.invoke(obj, "shzs.webservice");
            }
            Method setModifyTime = clazz.getMethod("setModifyTime", String.class);
            setModifyTime.invoke(obj, DateUtil.getCurrDateTime());
            Method setModifyUserFlow = clazz.getMethod("setModifyUserFlow", String.class);
            setModifyUserFlow.invoke(obj, "shzs.webservice");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
