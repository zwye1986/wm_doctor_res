package com.pinde.sci.keyUtil;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.common.InitConfig;
import org.apache.commons.lang.time.DateFormatUtils;

/**
 * Created by pdkj on 2018/4/23.
 */
public class PdUtil {

    public static boolean findChineseOrWestern(String rotationTypeId,String recTypeId) {
        if(StringUtil.isBlank(rotationTypeId)||StringUtil.isBlank(recTypeId)){
            return false;
        }
        String value= InitConfig.getSysCfg(recTypeId+"_medicine_type");
        if(StringUtil.isBlank(value))
        {
            return false;
        }
        if(!value.contains(rotationTypeId))
        {
            return false;
        }
        return  true;
    }

    /**
     *取得当前系统时间：具体格式为yyyyMMddHHmmss
     * @return
     */
    public static String getCurrDateTime2() {
        return DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss");
    }
}
