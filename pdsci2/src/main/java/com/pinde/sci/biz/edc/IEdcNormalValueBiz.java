package com.pinde.sci.biz.edc;

import com.pinde.sci.form.pub.PubFileForm;
import com.pinde.sci.model.mo.*;

import java.util.List;

public interface IEdcNormalValueBiz {

//	Map<String, List<EdcNormalValue>> getNormalValueMap(String projFlow,
//			String orgFlow);

    String addNormalValue(EdcNormalValue normalValue);

    void modifyNormalValue(EdcNormalValue normalValue);

    EdcNormalValue readNormalValue(String recordFlow);

    void copyRecord(EdcNormalValue normalValue);

    void delRecord(EdcNormalValue normalValue);

    void lockNormalValue(String projFlow, String orgFlow);

    EdcProjOrg searchEdcProjOrg(String projFlow, String orgFlow);

    void addNormalValueFile(String projFlow, String orgFlow, PubFileForm fileForm);

    void updateNormalValueFile(String projFlow, String orgFlow, PubFileForm fileForm);

    void unLockNormalValue(EdcProjOrg projOrg);


    List<EdcNormalValue> searchEdcNormalValue(EdcNormalValueExample example);

    EdcProjParam readProjParam(String projFlow);

    List<EdcNormalValue> searchNormalValue(String projFlow, String orgFlow);

}  
  