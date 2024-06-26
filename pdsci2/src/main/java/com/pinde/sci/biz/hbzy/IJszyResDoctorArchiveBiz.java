package com.pinde.sci.biz.hbzy;

import com.pinde.sci.model.hbzy.JszyDoctorInfoLogExt;
import com.pinde.sci.model.hbzy.JszyResArchDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import org.dom4j.DocumentException;

import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2017-05-22.
 */
public interface IJszyResDoctorArchiveBiz {
	/**
	 * 当前存档时间是否存在
	 * @param archiveTime
	 * @return
	 */
	int checkArchive(String archiveTime, String sessionNumber);

	boolean saveArchiveInfo(String archiveTime, String sessionNumber) throws DocumentException;

	List<ResArchiveSequence> allResArchiveSequence();

	List<JszyResArchDoctorRecruitExt> searchDoctorInfoExts(Map<String, Object> doctorRecruitMap);

	List<ResDoctorRecruitWithBLOBs> searchResDoctorRecruitList(Map<String,Object> paramMap);

	List<SysUser> searchSysUserLogList(Map<String,Object> paramMap);

	List<ResDoctorLog> searchResDoctorLogList(ResDoctorLog resDoctorLog);

	List<PubUserResume> searchResUserResumeLog(Map<String,Object> paramMap);

	List<JszyDoctorInfoLogExt> searchDoctorInfoResume(Map<String,Object> paramMap);

	ResArchiveSequence searchResArchiveSequenceByPK(String archiveFlow);

	List<DoctorAuth> searchAuthLogList(Map<String, Object> authParamMap);
}
