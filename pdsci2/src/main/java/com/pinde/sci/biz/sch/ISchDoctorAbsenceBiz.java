package com.pinde.sci.biz.sch;

import com.pinde.core.model.SchDoctorAbsence;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


public interface ISchDoctorAbsenceBiz {

	SchDoctorAbsence readSchDoctorAbsence(String absenceFlow);
	
	int saveSchDoctorAbsence(SchDoctorAbsence doctorAbsence);
//	int editSchDoctorAbsence(SchDoctorAbsence doctorAbsence, MultipartFile multipartFile) throws ParseException;

	List<SchDoctorAbsence> searchSchDoctorAbsenceByOrg(String orgFlow);

	List<SchDoctorAbsence> searchSchDoctorAbsenceList(SchDoctorAbsence doctorAbsence);

	List<SchDoctorAbsence> searchSchDoctorAbsenceByDoctor(String doctorFlow);
	
	List<SchDoctorAbsence> searchSchDoctorAbsenceByDoctorDept(String schDeptFlow,String doctorFlow);

	List<SchDoctorAbsence> searchDoctorAbsence(Map<String, Object> paramMap);

	int checkDates(String userFlow, String startDate1, String endDate2, String absenceFlow);

	int editSchDoctorAbsence2(SchDoctorAbsence doctorAbsence, MultipartFile file);
}
