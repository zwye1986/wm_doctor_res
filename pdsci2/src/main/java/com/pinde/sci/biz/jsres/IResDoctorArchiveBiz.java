package com.pinde.sci.biz.jsres;

import com.pinde.core.model.*;
import com.pinde.sci.model.jsres.JsDoctorInfoLogExt;
import com.pinde.sci.model.jsres.JsResArchDoctorRecruitExt;
import com.pinde.sci.model.mo.ResUserResumeLog;
import com.pinde.core.model.SysUserLog;
import org.dom4j.DocumentException;

import java.util.List;

/**
 * Created by www.0001.Ga on 2017-05-22.
 */
public interface IResDoctorArchiveBiz {
	/**
	 * 当前存档时间是否存在
	 * @param archiveTime
	 * @return
	 */
	int checkArchive(String archiveTime);

	boolean saveArchiveInfo(String archiveTime) throws DocumentException;

	List<ResArchiveSequence> allResArchiveSequence();

    List<JsResArchDoctorRecruitExt> searchDoctorInfoExts(String archiveFlow, ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList, String flag, List<String> docTypeList);

	List<ResDoctorRecruitLogWithBLOBs> searchResDoctorRecruitList(ResDoctorRecruitLog recruit, String orderByClause);

	List<SysUserLog> searchSysUserLogList(SysUserLog sysUserLog);

	List<ResDoctorLog> searchResDoctorLogList(ResDoctorLog resDoctorLog);

	List<ResUserResumeLog> searchResUserResumeLog(ResUserResumeLog userResumeLog);

    List<JsDoctorInfoLogExt> searchDoctorInfoResume(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList, String flag, List<String> docTypeList, String archiveFlow);
}
