package com.pinde.sci.biz.hbzy;

import com.pinde.sci.model.mo.JsresGraduationApply;
import com.pinde.sci.model.mo.JsresGraduationApplyLog;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by pdkj on 2018/3/9.
 */
public interface IJszyGraduationApplyBiz {

    JsresGraduationApply searchByRecruitFlow(String recruitFlow, String applyYear);

    List<JsresGraduationApply> searchByRecruitFlows(List<String> recruitFlows, String applyYear);

    int editGraduationApply(JsresGraduationApply jsresGraduationApply);

    List<Map<String,Object>> chargeQueryApplyList(Map<String, Object> param);

    List<JsresGraduationApplyLog> getAuditLogsByApplyFlow(String applyFlow);

    JsresGraduationApply readByFlow(String applyFlow);

    List<Map<String,Object>> chargeQueryListForExport(Map<String, Object> param);

    int saveBatchAudit(List<JsresGraduationApply> applies, List<JsresGraduationApplyLog> logs);

    void chargeExportList(List<Map<String,Object>> list, HttpServletResponse response, String isWaitAudit, String roleFlag) throws IOException;

    int saveChargeAudit(JsresGraduationApply apply, JsresGraduationApplyLog log);
}
