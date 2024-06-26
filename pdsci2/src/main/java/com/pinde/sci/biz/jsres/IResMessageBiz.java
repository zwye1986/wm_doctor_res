package com.pinde.sci.biz.jsres;

import com.pinde.sci.model.mo.*;
import org.dom4j.DocumentException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author tiger
 */
public interface IResMessageBiz {

    List<ResMessage> searchMessageByOrg(String orgFlow,String messageTitle);

    ResMessage findMessageByFlow(String messageFlow);

    List<ResMessage> findMessageByOrgFlow(String orgFlow,String sessionNumber);

    int editMessage(ResMessage message);

    int delMessage(String messageFlow);

    List<ResMessage> findMessage(ResMessage message);

    List<Map<String,Object>> findMessageOrgAndCount(String sessionNumber);

    List<Map<String,Object>> findMessageOrgAndTime(String time);
}
