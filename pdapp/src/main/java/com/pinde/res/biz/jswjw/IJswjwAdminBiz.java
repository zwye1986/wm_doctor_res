package com.pinde.res.biz.jswjw;

import com.pinde.core.model.ResDocotrDelayTeturn;
import com.pinde.core.model.ResOrgSpe;
import com.pinde.core.model.SysDept;
import com.pinde.core.model.SysUser;
import com.pinde.core.model.JsResDoctorOrgHistoryExt;

import java.util.List;
import java.util.Map;

public interface IJswjwAdminBiz {


    List<Map<String,String>> schDeptDoctorSchProcess(Map<String, String> schArrangeResultMap);

    List<SysDept> getErrorSchDepts(String orgFlow);

    void sendErrorSchNotice(List<String> doctorFlows, String content, SysUser user);


    List<JsResDoctorOrgHistoryExt> getSpeChangeList(Map<String,Object> param);

    List<ResDocotrDelayTeturn> getOrgDelayReturnInfo(Map<String, Object> param, List<String> flags);

    List<ResOrgSpe> getOrgSpes(String orgFlow);

    List<Map<String,Object>> getOrgSpeDocNum(String orgFlow,String sessionNumber);

    int getOrgDocCount(String orgFlow, String sessionNumber, String statusId);

    List<Map<String,String>> getOrgDocList(Map<String, Object> param);

}
  