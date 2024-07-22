package com.pinde.sci.biz.jsres;

import com.pinde.sci.model.mo.JsresUserBalcklist;
import org.dom4j.DocumentException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author chenzuozhou
 *Created by pdkj on 2016/8/30.
 */
public interface IJsResUserBlackBiz {
    /**
     * 获取黑名单
     * @param jsresUserBalcklist,operUserFlows,orgFlowList
     * @return
     */
    List<JsresUserBalcklist> searchInfo(JsresUserBalcklist jsresUserBalcklist,List<String> userFlowList,List<String> orgFlowList);

    List<JsresUserBalcklist> searchInfo2(JsresUserBalcklist jsresUserBalcklist,List<String> userFlowList,
                                         List<String> orgFlowList,List<String> sessionNumbers,String auditStatusId);

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

    JsresUserBalcklist readUserBalcklist(String recordFlow);

    //上传附件
    String checkImg(MultipartFile uploadFile);

    String saveFileToDirs(String oldFolderName, MultipartFile uploadFile, String folderName, String planYear);

}
