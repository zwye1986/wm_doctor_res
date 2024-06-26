package com.pinde.sci.biz.hbres;

import com.pinde.sci.model.mo.JsresGraduationApply;
import com.pinde.sci.model.mo.JsresGraduationApplyLog;
import com.pinde.sci.model.mo.JsresGraduationAttachment;
import org.dom4j.DocumentException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by czz on 2017/02/27 0016.
 */
public interface IHbResGraduationApplyBiz {

    JsresGraduationApply searchByRecruitFlow(String recruitFlow, String applyYear);

    int editGraduationApply(JsresGraduationApply jsresGraduationApply);

    List<JsresGraduationApplyLog> getAuditLogsByApplyFlow(String applyFlow);

    JsresGraduationApply readByFlow(String applyFlow);

    int saveChargeAudit(JsresGraduationApply apply, JsresGraduationApplyLog log);

    void chargeExportList(List<Map<String, Object>> list, HttpServletResponse response, String isWaitAudit) throws IOException;

    int saveBatchAudit(List<JsresGraduationApply> applies, List<JsresGraduationApplyLog> logs);

    List<Map<String,Object>> chargeQueryListForExport(Map<String, Object> param);

    void addOldInfoByApplyYear(String applyYear, String recruitFlow, String doctorFlow, String applyFlow, Map<String, String> practicingMap, List<String> resultFlows) throws DocumentException;

    int editGraduationApply2(JsresGraduationApply jsresGraduationApply, String recruitFlow, String doctorFlow, String applyYear, Map<String, String> practicingMap, List<String> resultFlows) throws DocumentException;

    List<Map<String,Object>> chargeQueryApplyList(Map<String, Object> param);

    int editAttachment(JsresGraduationAttachment attachment);

    List<JsresGraduationAttachment> searchAttachments(JsresGraduationAttachment attachment);

    Map<String, String> graduationImgUpload(MultipartFile file);
}