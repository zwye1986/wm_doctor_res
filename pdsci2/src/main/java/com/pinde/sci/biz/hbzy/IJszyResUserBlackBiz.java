package com.pinde.sci.biz.hbzy;

import com.pinde.sci.model.mo.JsresUserBalcklist;
import org.dom4j.DocumentException;

import java.util.List;
import java.util.Map;

/**
 * @author chenzuozhou
 *Created by pdkj on 2016/8/30.
 */
public interface IJszyResUserBlackBiz {
    /**
     * 获取黑名单
     * @param jsresUserBalcklist,operUserFlows,orgFlowList
     * @return
     */
    List<JsresUserBalcklist> searchInfo(JsresUserBalcklist jsresUserBalcklist, List<String> userFlowList, List<String> orgFlowList);

    Map<Object, Object> paraseBlackInfo(List<JsresUserBalcklist> blackLists, String sessionNumber, String speName) throws DocumentException;

    /**
     * 保存，修改黑名单
     * @param black
     * @return
     */
    int saveBlack(JsresUserBalcklist black);

    /**
     * 根据用户ID查询黑名单
     * @param userIdNo
     * @return
     */
    JsresUserBalcklist searchInfoByIdNo(String userIdNo);
}
