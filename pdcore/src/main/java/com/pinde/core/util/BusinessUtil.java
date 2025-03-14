package com.pinde.core.util;

import com.pinde.core.common.sci.dao.JsresPowerCfgMapper;
import com.pinde.core.model.JsresPowerCfg;
import com.pinde.core.model.ResSchProcessExpress;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
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

    public static synchronized void getImageList(List<ResSchProcessExpress> rec, List<Map<String, Object>> imagelist) {
        rec.stream().filter(e -> StringUtils.isNotEmpty(e.getRecContent())).forEach(e -> {
            Document doc = null;
            try {
                doc = DocumentHelper.parseText(e.getRecContent());
            } catch (DocumentException ex) {
                log.error("parse xml error,content={}",e.getRecContent(), ex);
                return;
            }
            Element root = doc.getRootElement();
            List<Element> imageEles = root.elements();
            if (imageEles != null && imageEles.size() > 0) {
                for (Element image : imageEles) {
                    Map<String, Object> recContent = new HashMap<String, Object>();
                    List<Element> elements = image.elements();
                    for (Element attr : elements) {
                        String attrName = attr.getName();
                        String attrValue = attr.getText();
                        recContent.put(attrName, attrValue);
                    }
                    imagelist.add(recContent);
                }
            }
        });
    }
}
