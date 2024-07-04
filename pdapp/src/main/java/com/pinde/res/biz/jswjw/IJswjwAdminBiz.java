package com.pinde.res.biz.jswjw;

import com.pinde.res.model.jswjw.mo.JsResDoctorOrgHistoryExt;
import com.pinde.sci.model.mo.ResDocotrDelayTeturn;
import com.pinde.sci.model.mo.ResOrgSpe;
import com.pinde.sci.model.mo.SysDept;
import com.pinde.sci.model.mo.SysUser;

import java.util.List;
import java.util.Map;

public interface IJswjwAdminBiz {


    List<Map<String,String>> schDeptDoctorSchProcess(Map<String, String> schArrangeResultMap);

    List<SysDept> getErrorSchDepts(String orgFlow);

    void sendErrorSchNotice(List<String> doctorFlows, String content,SysUser user);


    List<JsResDoctorOrgHistoryExt> getSpeChangeList(Map<String,Object> param);

    List<ResDocotrDelayTeturn> getOrgDelayReturnInfo(Map<String, Object> param,List<String> flags);

    List<ResOrgSpe> getOrgSpes(String orgFlow);

    List<Map<String,Object>> getOrgSpeDocNum(String orgFlow,String sessionNumber);

    int getOrgDocCount(String orgFlow, String sessionNumber, String statusId);

    List<Map<String,String>> getOrgDocList(Map<String, Object> param);

}
  