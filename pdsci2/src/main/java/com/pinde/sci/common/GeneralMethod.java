package com.pinde.sci.common;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.core.common.enums.sys.ReqTypeEnum;
import com.pinde.sci.model.mo.SysLog;
import com.pinde.sci.model.mo.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class GeneralMethod {
    private static Logger logger = LoggerFactory.getLogger(GeneralMethod.class);

    public static void setRecordInfo(Object obj, boolean isAdd) {
        SysUser currUser = GlobalContext.getCurrentUser();
        Class<?> clazz = obj.getClass();
        try {
            if (isAdd) {
                Method setRecordStatusMethod = clazz.getMethod("setRecordStatus", String.class);
                setRecordStatusMethod.invoke(obj, com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                Method setCreateTime = clazz.getMethod("setCreateTime", String.class);
                setCreateTime.invoke(obj, DateUtil.getCurrDateTime());
                Method setCreateUserFlow = clazz.getMethod("setCreateUserFlow", String.class);
                if (currUser != null) {
                    setCreateUserFlow.invoke(obj, currUser.getUserFlow());
                }
            }
            Method setModifyTime = clazz.getMethod("setModifyTime", String.class);
            setModifyTime.invoke(obj, DateUtil.getCurrDateTime());
            Method setModifyUserFlow = clazz.getMethod("setModifyUserFlow", String.class);
            if (currUser != null) {
                setModifyUserFlow.invoke(obj, currUser.getUserFlow());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String toHTML(String value) {
        //String result="";
        //String javaString = StringEscapeUtils.escapeJava(value);
        //System.out.println(javaString+"============");
        if (StringUtil.isNotBlank(value)) {
            value = value.replaceAll("\n", "<br/>");
        } else {
            value = "";
        }

        return value;
    }

    public static String getPercent(Integer x, Integer total) {
        String result = "";//接受百分比的值
        if (x == null) {
            x = 0;
        }
        double xtmp = x * 1.0;
        if (total != null && total != 0) {
            double tempresult = xtmp / total;
            DecimalFormat df1 = new DecimalFormat("0.00%");    //##.00%   百分比格式，后面不足2位的用0补齐
            result = df1.format(tempresult);
        } else {
            result = "0";
        }

        return result;
    }

    /**
     * 计算百分比
     * 最大值 100%
     * @param x
     * @param total
     * @return
     */
    public static String getPercent2(Integer x, Integer total) {
        String result = "";//接受百分比的值
        if (x == null) {
            x = 0;
        }
        double xtmp = x * 1.0;
        if (total != null && total != 0) {
            double tempresult = xtmp / total;
            if (tempresult>1){
                tempresult=1;
            }
            DecimalFormat df1 = new DecimalFormat("0.00%");    //##.00%   百分比格式，后面不足2位的用0补齐
            result = df1.format(tempresult);
        } else {
            result = "0";
        }

        return result;
    }

    public static String getPercentByString(String a, String b) {
        int x = 0;
        int total = 0;
        if (StringUtil.isNotBlank(a)) {
            x = Integer.parseInt(a);
        }
        if (StringUtil.isNotBlank(b)) {
            total = Integer.parseInt(b);
        }
        String result = "";//接受百分比的值
        double xtmp = x * 1.0;
        if (total != 0) {
            double tempresult = xtmp / total;
            DecimalFormat df1 = new DecimalFormat("0.00%");    //##.00%   百分比格式，后面不足2位的用0补齐
            result = df1.format(tempresult);
        } else {
            result = "0";
        }

        return result;
    }

    public static String getPercentByDouble(Double x, Double total) {
        String result = "";//接受百分比的值
        if (x == null) {
            x = new Double("0");
        }
        if (total != null && total != 0) {
            double tempresult = x / total;
            DecimalFormat df1 = new DecimalFormat("0.00%");    //##.00%   百分比格式，后面不足2位的用0补齐
            result = df1.format(tempresult);
        } else {
            result = "0";
        }

        return result;
    }

    public static void addSysLog(SysLog log) {
        SysUser user = GlobalContext.getCurrentUser();
        log.setLogFlow(PkUtil.getUUID());
        log.setUserFlow(user.getUserFlow());
        log.setUserCode(user.getUserCode());
        log.setUserName(user.getUserName());
        log.setOrgFlow(user.getOrgFlow());
        log.setOrgName(user.getOrgName());
        log.setDeptFlow(user.getDeptFlow());
        log.setDeptName(user.getDeptName());
        if (StringUtil.isBlank(log.getReqTypeId())) {
            log.setReqTypeId(ReqTypeEnum.GET.getId());
        }
        log.setReqTypeName(ReqTypeEnum.getNameById(log.getReqTypeId()));
        log.setLogTime(DateUtil.getCurrDateTime());
        if (StringUtil.isBlank(log.getWsId())) {
            log.setWsId(GlobalContext.getCurrentWsId());
        }
        log.setWsName(InitConfig.getWorkStationName(log.getWsId()));
        setRecordInfo(log, true);
    }

    public static String tranNum(String value, String digit) {
        BigDecimal divider = new BigDecimal(1);
        BigDecimal ten = new BigDecimal(10);
        BigDecimal dividered = new BigDecimal(value);
        if (Integer.parseInt(digit) >= 1) {
            for (int i = 1; i <= Integer.parseInt(digit); i++) {
                divider = divider.multiply(ten);
            }
            dividered.divide(divider, 2, BigDecimal.ROUND_HALF_UP).toString();
            return "";
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(tranNum("3","2"));
    }
    public static String getWatermark(String watermarkFlag) {
        String watermark = "";
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(watermarkFlag)) {
            watermark = StringUtil.defaultIfEmpty(InitConfig.getSysCfg("srm_watermark_y"), "正式稿").trim();
        } else {
            watermark = StringUtil.defaultIfEmpty(InitConfig.getSysCfg("srm_watermark_n"), "").trim();
        }
        return watermark;
    }


    public static boolean canShowByVersion(String version) {
        boolean result = false;
        /*
        *global   卫生局版
        *charge   卫生局版 主管部门适用
        *local    医院版（通用）
        *local_common   医院版（普通版 徐州中心医院，无锡二院不适用）
        *local_xzzxyy   医院版 徐州中心医院适用
        *local_wxdermyy 医院版 无锡二院适用
        */
        if (StringUtil.isBlank(version)) {
            result = true;
        }
        if ("global".equals(version)) {
            result = "global".equals(InitConfig.getSysCfg("srm_for_use"));
        }
        if ("charge".equals(version)) {
            result = "global".equals(InitConfig.getSysCfg("srm_for_use")) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("srm_for_charge_use"));
        }
        if ("local".equals(version)) {
            result = "local".equals(InitConfig.getSysCfg("srm_for_use"));
        }
        //普通院版科研系统
        if("local_common".equals(version)){
            //srm_local_type值为‘common’或为空
            if( "local".equals(InitConfig.getSysCfg("srm_for_use")) && ("common".equals(InitConfig.getSysCfg("srm_local_type"))||StringUtil.isBlank(InitConfig.getSysCfg("srm_local_type")))){
                result = true;
            }
        }
        //徐州中心医院版科研系统
        if("local_xzzxyy".equals(version)){
            if ("local".equals(InitConfig.getSysCfg("srm_for_use")) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("srm_local_type"))) {
                result = true;
            }
        }
        //无锡二院版科研系统
        if("local_wxdermyy".equals(version)){
            if( "local".equals(InitConfig.getSysCfg("srm_for_use")) && "wxdermyy".equals(InitConfig.getSysCfg("srm_local_type"))){
                result = true;
            }
        }
        return result;
    }
}
