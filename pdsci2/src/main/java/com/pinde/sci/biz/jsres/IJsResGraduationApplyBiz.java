package com.pinde.sci.biz.jsres;

import com.pinde.core.model.*;
import org.dom4j.DocumentException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by czz on 2017/02/27 0016.
 */
public interface IJsResGraduationApplyBiz {

    JsresGraduationApply searchByRecruitFlow(String recruitFlow, String applyYear);

    int editGraduationApply(JsresGraduationApply jsresGraduationApply);

    List<Map<String,Object>> chargeQueryList(Map<String, Object> param);

    List<JsresGraduationApplyLog> getAuditLogsByApplyFlow(String applyFlow);

    JsresGraduationApply readByFlow(String applyFlow);

    int saveChargeAudit(JsresGraduationApply apply, JsresGraduationApplyLog log);

    void chargeExportList(List<Map<String, Object>> list, HttpServletResponse response,String isWaitAudit) throws IOException;

    void chargeExportListTwo(List<Map<String, Object>> list, HttpServletResponse response,String isWaitAudit) throws IOException;

    int saveBatchAudit(List<JsresGraduationApply> applies, List<JsresGraduationApplyLog> logs);

    List<Map<String,Object>> chargeQueryListForExport(Map<String, Object> param);

    void addOldInfoByApplyYear(String applyYear, String recruitFlow, String doctorFlow, String applyFlow, Map<String, String> practicingMap, String rotationFlow, String reSubmitFlag) throws DocumentException;

    int editGraduationApply2(JsresGraduationApply jsresGraduationApply, String recruitFlow, String changeSpeId, String doctorFlow, String applyYear, Map<String, String> practicingMap, String rotationFlow, String reSubmitFlag) throws DocumentException;

    List<Map<String,Object>> chargeQueryApplyList(Map<String, Object> param);

    int updatePer(String applyFlow, String recruitFlow, String s, String doctorFlow, String applyYear);

    List<JsresGraduationInfo> queryGraduationInfoList(Map<String, Object> param);

    List<Map<String,Object>> queryGraduationInfoListExport(Map<String, Object> param);

    void chargeExportListNew(List<Map<String,Object>> list, HttpServletResponse response, String isWaitAudit) throws IOException;

    List<Map<String,Object>> chargeQueryApplyListNew(Map<String, Object> param);

    void chargeExportList2(List<Map<String,Object>> list, HttpServletResponse response, String isWaitAudit,String roleFlag) throws IOException;
    void chargeExportList3(List<Map<String,Object>> list, HttpServletResponse response, String isWaitAudit,String roleFlag) throws IOException;

    void exportCountryList(List<Map<String,Object>> list, HttpServletResponse response) throws IOException;

    List<Map<String,Object>> chargeQueryListForExport2(Map<String, Object> param);

    List<Map<String,Object>> chargeQueryApplyList2(Map<String, Object> param);

    /**
     * @Department：研发部
     * @Description 查询异常报考记录学员信息
     * @Author fengxf
     * @Date 2025/2/17
     */
    GraduationDoctorTemp getGraduationDoctorTemp(String doctorFlow);

    Map<String, List<ResRec>> getNonComplianceRecords(List<String> doctorFlow);

}