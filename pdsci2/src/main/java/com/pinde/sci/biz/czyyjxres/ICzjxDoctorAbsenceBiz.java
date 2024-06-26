package com.pinde.sci.biz.czyyjxres;


import com.pinde.sci.model.mo.SchDoctorAbsence;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ICzjxDoctorAbsenceBiz {
    SchDoctorAbsence readSchDoctorAbsence(String absenceFlow);

    int saveSchDoctorAbsence(SchDoctorAbsence doctorAbsence);

    /**
     * 编辑请假记录
     * @param doctorAbsence
     * @param multipartFile
     * @return
     */
    int editSchDoctorAbsence(SchDoctorAbsence doctorAbsence, MultipartFile multipartFile);

    List<SchDoctorAbsence> searchSchDoctorAbsenceByOrg(String orgFlow);

    List<SchDoctorAbsence> searchSchDoctorAbsenceList(SchDoctorAbsence doctorAbsence);

    List<SchDoctorAbsence> searchSchDoctorAbsenceByDoctor(String doctorFlow);

    List<SchDoctorAbsence> searchSchDoctorAbsenceByDoctorDept(String schDeptFlow, String doctorFlow);

    List<SchDoctorAbsence> searchDoctorAbsence(Map<String, Object> paramMap);

    /**
     * 计算一组医师的请假总天数
     * @param doctorFlows
     * @return
     */
    List<Map<String, Object>> countAbsenceDays(List<String> doctorFlows);
}
