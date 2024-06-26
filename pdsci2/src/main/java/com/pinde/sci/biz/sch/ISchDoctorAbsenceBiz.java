package com.pinde.sci.biz.sch;

import com.pinde.sci.model.mo.SchDoctorAbsence;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;
import java.util.Map;


public interface ISchDoctorAbsenceBiz {

	SchDoctorAbsence readSchDoctorAbsence(String absenceFlow);
	
	int saveSchDoctorAbsence(SchDoctorAbsence doctorAbsence);

	/**
	 * 编辑请假记录
	 * @param doctorAbsence
	 * @param multipartFile
	 * @return
	 */
	int editSchDoctorAbsence(SchDoctorAbsence doctorAbsence, MultipartFile multipartFile) throws ParseException;

	List<SchDoctorAbsence> searchSchDoctorAbsenceByOrg(String orgFlow);

	List<SchDoctorAbsence> searchSchDoctorAbsenceList(SchDoctorAbsence doctorAbsence);

	List<SchDoctorAbsence> searchSchDoctorAbsenceByDoctor(String doctorFlow);
	
	List<SchDoctorAbsence> searchSchDoctorAbsenceByDoctorDept(String schDeptFlow,String doctorFlow);

	List<SchDoctorAbsence> searchDoctorAbsence(Map<String, Object> paramMap);

	/**
	 * 计算一组医师的请假总天数
	 * @param doctorFlows
	 * @return
	 */
	List<Map<String, Object>> countAbsenceDays(List<String> doctorFlows);

	//查询当前年份某学员请了的年假
	List<SchDoctorAbsence> getCurrentYearLeaves(String absenceFlow,String currentYear,String doctorFlow);

	int checkDates(String userFlow, String startDate1, String endDate2, String absenceFlow);

	//导入缺勤
	int importDict(MultipartFile file);

	int editSchDoctorAbsence2(SchDoctorAbsence doctorAbsence, MultipartFile file);
}
