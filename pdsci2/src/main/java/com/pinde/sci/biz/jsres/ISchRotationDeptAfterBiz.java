package com.pinde.sci.biz.jsres;

import com.pinde.core.model.ResDoctorSchProcess;
import com.pinde.core.model.ResSchProcessExpress;
import com.pinde.core.model.SchRotationDeptAfterWithBLOBs;
import com.pinde.sci.common.util.ExcelUtile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author tiger
 */
public interface ISchRotationDeptAfterBiz {
    List<SchRotationDeptAfterWithBLOBs> getAll();

    void updateNAllInfo(String applyYear);

    int edit(SchRotationDeptAfterWithBLOBs after);

    List<SchRotationDeptAfterWithBLOBs> getAfterByDoctorFlow(String doctorFlow, String applyYear);

    void updateRecruitAsseInfo(String applyYear);


    int  updateUriAuditInfo();

    void updateDeptDetail(String applyYear);

    void updateDeptTemp(String applyYear);

    void updateDeptAvgTemp(String applyYear);

    int  updateRecruitAvgTemp(String applyYear);

    ExcelUtile chouquPhoto(MultipartFile file, String dirUrl);

    List<SchRotationDeptAfterWithBLOBs> getAllByApplyYear(String applyYear);

    void saveRegisteManua(String registeManua, String recruitFlow, String applyYear);

    List<ResDoctorSchProcess> queryProcess(String userCode);

    int delProcessType(String processFlow, String recTypeId);

    int delProcess(String processFlow);

    List<Map<String,String>> queryApplyList(String applyYear);

    List<ResDoctorSchProcess> queryDelProcess(String userCode);

    int backProcess(String processFlow);

    void updateResultAfterPic(String have_after_pic, String processFlow, String operUserFlow);

    void examTeaRole();

    int backAttend(String userCode, String startDate, String endDate);

    void batchOscaSubmit(String orgFlow);

    void updateResultAfterPicNotHaveRec();

    void updateMonthStatistics(String month);

    void updateMonthAppStatistics(String month);

    ExcelUtile addTempIdNo(MultipartFile file);

    ExcelUtile addUserInfo(MultipartFile file);

    ExcelUtile upDateRecruitInfo(MultipartFile file);

    ExcelUtile updateReturenInfo(MultipartFile file);

    ExcelUtile updateRecruitOrgInfo(MultipartFile file);
    ExcelUtile updateRecruitSpeInfo(MultipartFile file);

    List<ResSchProcessExpress> updateAfterEvalutaion();

    void updateAfter(ResSchProcessExpress express);

    int backProcessType(String processFlow, String recTypeId);

    void delProcessTypeByFlow(String recFlow);

    int backProcessTypeByFlow(String recFlow);

    List<ResSchProcessExpress> searchRecByProcessWithBLOBsByDel(List<String> recTypeIds, String processFlow);

    List<Map<String,String>> afterAttendBackList(String userCode, String startDate, String endDate);

    int backAttendByFlow(String attendanceFlow);

    ExcelUtile insertSptUserInfo(MultipartFile file);
    ExcelUtile insertGjptUserInfo(MultipartFile file);

    void updateNotStudy();


}

