package com.pinde.core.util;

import com.pinde.core.common.sci.dao.JsresPowerCfgMapper;
import com.pinde.core.model.JsresPowerCfg;

public class BusinessUtil {
    public static String jsresPowerCfgMap (String cfgCode){
        JsresPowerCfgMapper jsresPowerCfgMapper = SpringUtil.getBean(JsresPowerCfgMapper.class);
        JsresPowerCfg jsresPowerCfg = jsresPowerCfgMapper.selectByPrimaryKey(cfgCode);
        String value = "";
        if(jsresPowerCfg != null){
            value = jsresPowerCfg.getCfgValue();
        }
        return value;
    }
}
