package com.pinde.sci.biz.sch;

import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.ctrl.sch.plan.domain.Plan;
import com.pinde.sci.form.sch.SchArrangeForm;
import com.pinde.sci.form.sch.SchSelectDeptForm;
import com.pinde.sci.model.mo.SchArrangeResult;
import com.pinde.sci.model.mo.SchDoctorSelectDept;
import com.pinde.sci.model.mo.SchOrgArrangeResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ISchDoctorSelectDeptBiz {

    List<SchDoctorSelectDept> findSelectDepts(String doctorFlow);

    List<SchOrgArrangeResult> findSchResults(String doctorFlow);

    List<SchDoctorSelectDept> readSelectDepts(String doctorFlow, String sessionNumber, String rotationFlow, String orgFlow, String cycleType);

    void saveSelDept(List<SchSelectDeptForm> selDepts, String orgFlow, String doctorFlow, String sessionNumber, String rotationFlow) throws Exception;

    List<SchOrgArrangeResult> readArrangeResults(String doctorFlow, String sessionNumber, String rotationFlow, String orgFlow);

    int findSesssionNumberResults(String sessionNumber, String orgFlow);

    void delArrangeResult(SchArrangeForm form);

    int findDeptMonthUseNum(String deptFlow, String startDate, String endDate);

    Double getRotationCycleMonthNum(String rotationFlow, String selectYear, String cycleYear, String orgFlow, String sessionNumber);

    void saveArrangeResult(Plan solvedCloudBalance, SchArrangeForm form) throws Exception;

    List<SchOrgArrangeResult> readOrgArrangeResult(Map<String, String> param);

    List<SchOrgArrangeResult> readArrangeResultsByMap(Map<String, String> param);

    int findCycleResultCount(String doctorFlow, String cycleYear);

    void syncArrange(SchArrangeForm form);

    int findSchResultCount(String doctorFlow, String cycleYear);

    List<SchArrangeResult> getCycleResults(String doctorFlow, String sessionNumber, String rotationFlow, String orgFlow);

    int saveRostering(String doctorFlow, String groupFlow, String standardDeptId, String standardDeptName, String schDeptFlow, String resultFlow);

    ExcelUtile importArrange(MultipartFile file);

    List<Map<String,String>> readOrgArrangeStartDate(String doctorFlow, String sessionNumber, String rotationFlow, String orgFlow);

    ExcelUtile importArrange2(MultipartFile file, String startDate, String endDate);

    void saveArrangeTime(List<SchOrgArrangeResult> results);
}
